package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class FractalExplorer
{
    private final int displaySize;
    private final JImageDisplay display;
    private final FractalGenerator fractalGenerator;
    private final Rectangle2D.Double range;

    public FractalExplorer(int size)
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
        
        frame.add(display, BorderLayout.CENTER); //дисплей
        
        JButton rbut = new JButton("Reset"); //кнопка сброса

        Reset handler = new Reset();
        rbut.addActionListener(handler);

        frame.add(rbut, BorderLayout.SOUTH);

        Mouse click = new Mouse();//мышь
        display.addMouseListener(click);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //для выхода

        frame.pack();//финальные действия по интерфейсу виджета
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() //отображает фрактал
    {
        for (int x=0; x<displaySize; x++)
        {
            for (int y=0; y<displaySize; y++)
            {
                //нахождение x и y
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
                //получение числа иттераций
                int iteration = fractalGenerator.numIterations(xCoord, yCoord);
                if (iteration == -1)
                {
                    display.drawPixel(x, y, 0);
                }
                else
                {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor); //рисовка пикселей
                }
            }
        }
        //перерисовка
        display.repaint();
    }

    private class Reset implements ActionListener
    {
        public void actionPerformed(ActionEvent e) //сброс к начальному значению и рисовка фрактала
        {
            fractalGenerator.getInitialRange(range);
            drawFractal();
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