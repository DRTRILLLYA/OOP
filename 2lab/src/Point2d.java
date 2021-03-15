public class Point2d {
    private double Cx;
    private double Cy;

    public Point2d ( double x, double y)
    {
        Cx = x;
        Cy = y;
    }

    public Point2d ()
    {
        this(0, 0);
    }

    public double getX ()
    {
        return Cx;
    }

    public double getY ()
    {
        return Cy;
    }

    public void setX ( double val)
    {
        Cx = val;
    }

    public void setY ( double val)
    {
        Cy = val;
    }
}