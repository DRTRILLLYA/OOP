package com.company;

import com.company.FractalGenerator;
import com.company.JImageDisplay;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.filechooser.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

public class FractalExplorer
{
    private final int displaySize;
    private final JImageDisplay display;
    private FractalGenerator fractalGenerator;
    private final Rectangle2D.Double range;

    public FractalExplorer(int size)  // создание интерфейса виджета
    {
        displaySize = size;
        fractalGenerator = new Mandelbrot(); //инициализация фрактала и прочих объектов
        range = new Rectangle2D.Double();
        fractalGenerator.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }

    public void createAndShowGUI()
    {
        display.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal"); //создание окна и титульная надпись
        frame.add(display, BorderLayout.CENTER);

        JButton rbut = new JButton("Reset"); //кнопка сброса

        ButtonHandler reset = new ButtonHandler();
        rbut.addActionListener(reset);

        Mouse click = new Mouse(); //мышь
        display.addMouseListener(click);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox comboBox = new JComboBox(); //выпадающий список

        FractalGenerator mandelbrotfr = new Mandelbrot();
        comboBox.addItem(mandelbrotfr);
        FractalGenerator tricornfr = new Tricorn();
        comboBox.addItem(tricornfr);
        FractalGenerator burningshipfr = new BurningShip();
        comboBox.addItem(burningshipfr);

        ButtonHandler fractalChoose = new ButtonHandler();
        comboBox.addActionListener(fractalChoose);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Choose Fractal:");
        panel.add(label);
        panel.add(comboBox);
        frame.add(panel, BorderLayout.NORTH);//лейбл с надписью и выравнивание по верху

        JButton sbut = new JButton("Save"); //кнопка сохранения
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
        for (int x=0; x < displaySize; x++)
        {
            for (int y=0; y < displaySize; y++)
            {
                double xCoord = fractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = fractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
                int iteration = fractalGenerator.numIterations(xCoord, yCoord);
                if (iteration == -1)
                {
                    display.drawPixel(x, y, 0);
                }

                else
                {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }

            }
        }
        display.repaint();//перерисовка
    }

    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String com = event.getActionCommand();
            if (event.getSource() instanceof JComboBox)
            {
                JComboBox source = (JComboBox) event.getSource();
                fractalGenerator = (FractalGenerator) source.getSelectedItem();
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
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
            int y = event.getY();
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);

            fractalGenerator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
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