package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent
{
    private BufferedImage image;

    public BufferedImage getImage()
    {
        return image;
    }

    public JImageDisplay (int width, int height)
    {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage()
    {
        int size = getWidth() * getHeight();
        int[] pixels = new int[size];
        image.setRGB(0, 0, getWidth(), getHeight(), pixels, 0, 1);
    }

    public void drawPixel(int x, int y, int color)
    {
        image.setRGB(x, y, color);
    }
}