package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.filechooser.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

public class FractalExplorer //конструктор
{
    private JButton sbut;
    private JButton rbut;
    private JComboBox comboBox;

    private final int displaySize;
    private int rowsRemaining;
    private final JImageDisplay display;
    private  FractalGenerator fractalGenerator;
    private final Rectangle2D.Double range;

    public FractalExplorer(int size)
    {
        displaySize = size;
        fractalGenerator = new Mandelbrot(); //инициализация фрактала и прочих объектов
        range = new Rectangle2D.Double();
        fractalGenerator.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }

    public void createAndShowGUI() // пользовательский интерфейс
    {
        display.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal"); //создание окна и титульная надпись
        frame.add(display, BorderLayout.CENTER);

        rbut = new JButton("Reset"); //кнопка сброса
        ButtonHandler reset = new ButtonHandler();
        rbut.addActionListener(reset);

        Mouse click = new Mouse(); //мышь
        display.addMouseListener(click);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //отработка выхода
        comboBox = new JComboBox(); //выпадающий список

        FractalGenerator mandelbrotFract = new Mandelbrot();
        comboBox.addItem(mandelbrotFract);
        FractalGenerator tricornFract = new Tricorn();
        comboBox.addItem(tricornFract);
        FractalGenerator burningShipFract = new BurningShip();
        comboBox.addItem(burningShipFract);

        ButtonHandler fractalChoose = new ButtonHandler();
        comboBox.addActionListener(fractalChoose);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Choose fractal:");
        panel.add(label);
        panel.add(comboBox);
        frame.add(panel, BorderLayout.NORTH);

        sbut = new JButton("Save");
        JPanel bottompanel = new JPanel();
        bottompanel.add(sbut);
        bottompanel.add(rbut);
        frame.add(bottompanel, BorderLayout.SOUTH);

        ButtonHandler saveHandler = new ButtonHandler();
        sbut.addActionListener(saveHandler);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal()
    {
        enableUI(false);
        rowsRemaining = displaySize;
        for (int x = 0; x < displaySize; x++)
        {
            FractalWorker tekrow = new FractalWorker(x);
            tekrow.execute();
        }
    }

    private void enableUI(boolean value) //вкл/откл интерфейс при отрисовке
    {
        comboBox.setEnabled(value);
        rbut.setEnabled(value);
        sbut.setEnabled(value);
    }

    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String com = event.getActionCommand();
            if (event.getSource() instanceof JComboBox)
            {
                JComboBox source = (JComboBox) event.getSource();
                fractalGenerator = (com.company.FractalGenerator) source.getSelectedItem();
                fractalGenerator.getInitialRange(range);
                drawFractal();

            }

            else if (com.equals("Reset"))
            {
                fractalGenerator.getInitialRange(range);
                drawFractal();
            }

            else if (com.equals("Save"))
            {
                JFileChooser fc = new JFileChooser();
                FileFilter extensionFilter = new FileNameExtensionFilter("PNG Images", "png");//фильтр для пнг
                fc.setFileFilter(extensionFilter);
                fc.setAcceptAllFileFilterUsed(false);
                int selection = fc.showSaveDialog(display);
                if (selection == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = fc.getSelectedFile();
                    String fileName = file.toString();
                    try
                    {
                        BufferedImage displayImg = display.getImage();
                        javax.imageio.ImageIO.write(displayImg, "png", file);
                    }
                    catch (Exception exception) //отработка ошибок
                    {
                        JOptionPane.showMessageDialog(display, exception.getMessage(), "Can not save image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private class Mouse extends MouseAdapter
    {
        //класс для перерисовки фрактала при приближении
        public void mouseClicked(MouseEvent event)
        {
            //получение координат нажатия мыши
            int x = event.getX();
            double xCoord = com.company.FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
            int y = event.getY();
            double yCoord = com.company.FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);

            fractalGenerator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    private class FractalWorker extends SwingWorker <Object, Object>
    {
        int yCoordinate;
        int[] RGBValues;

        private FractalWorker(int rowNum)
        {
            yCoordinate = rowNum;
        }

        protected Object doInBackground()
        {
            RGBValues = new int[displaySize];
            for (int i = 0; i < RGBValues.length; i++)
            {
                //получение координат
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, i);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, yCoordinate);
                int iter = fractalGenerator.numIterations(xCoord, yCoord);
                if (iter == -1)
                {
                    RGBValues[i] = 0;
                }
                else
                {
                    float hue = 0.7f + (float) iter / 200f;
                    int color = Color.HSBtoRGB(hue, 1f, 1f);
                    RGBValues[i] = color;
                }
            }
            return null;
        }

        protected void done()
        {
            for (int i = 0; i < RGBValues.length; i++)
            {
                display.drawPixel(i, yCoordinate, RGBValues[i]);
            }
            display.repaint(0, 0, yCoordinate, displaySize, 1);

            rowsRemaining--;
            if (rowsRemaining == 0)
            {
                enableUI(true);
            }
        }
    }

    //для запуска
    public static void main(String[] args)
    {
        FractalExplorer display = new FractalExplorer(800);
        display.createAndShowGUI();
        display.drawFractal();
    }
}