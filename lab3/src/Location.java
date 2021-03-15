import java.util.Objects;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    public boolean equals(Object obj)
    {
        if (obj instanceof Location)
        {
            Location other = (Location) obj;
            return xCoord == other.xCoord && yCoord == other.yCoord;
        }
        return false;
    }

    /**Overridden hashcode() method
     * Provides hashcode for each location**/
    public int hashCode()
    {
        int code = 25;
        code = 7 * code + xCoord;
        code = 7 * code + yCoord;
        return code;
    }

    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
}