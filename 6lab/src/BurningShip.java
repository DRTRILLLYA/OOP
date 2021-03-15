package com.company;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -2.5;
        range.width = 4;
        range.height = 4;
    }

    public int numIterations(double x, double y)
    {
        int numiter = 0;
        double zr = 0;
        double zi = 0;

        while (numiter < MAX_ITERATIONS && zr * zr + zi * zi < 4)
        {
            double zrnew = zr * zr - zi * zi + x;
            double zinew = 2 * Math.abs(zr) * Math.abs(zi) + y;
            zr = zrnew;
            zi = zinew;
            numiter += 1;
        }

        if (numiter == MAX_ITERATIONS)
        {
            return -1;
        }
        return numiter;
    }

    public String toString()
    {
        return "Burning ship";
    }
}