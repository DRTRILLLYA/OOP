public class Point3d {
    private double Cx;
    private double Cy;
    private double Cz;

    public Point3d(double x, double y, double z)
    {
        Cx = x;
        Cy = y;
        Cz = z;
    }

    public Point3d()
    {
        this(0.0, 0.0, 0.0);
    }

    public double getX()
    {
        return Cx;
    }

    public double getY()
    {
        return Cy;
    }

    public double getZ()
    {
        return Cz;
    }

    public void setX(double val)
    {
        this.Cx = val;
    }

    public void setY(double val)
    {
        this.Cy = val;
    }

    public void setZ(double val)
    {
        this.Cz = val;
    }

    public boolean isEqual(Point3d other)
    {
        return (this.Cx == other.Cx & this.Cy == other.Cy & this.Cz == other.Cz);
    }

    public double distanceTo(Point3d other)
    {
        return Math.round((Math.sqrt(Math.pow(this.Cx - other.Cx, 2)
                + Math.pow(this.Cy - other.Cy, 2) +
                Math.pow(this.Cz - other.Cz, 2))) * 100 / 100);
    }
}
