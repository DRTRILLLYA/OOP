import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /* A reference to the A* algorithm map*/
    private final Map2D map;

    private final HashMap<Location, Waypoint> openPeaks = new HashMap<> ();
    private final HashMap<Location, Waypoint> closePeaks = new HashMap<> ();


    /*Class constructor*/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /* Returns the A* algorithm map*/
    public Map2D getMap()
    {
        return map;
    }

    /* Scans through all open waypoints and returns the only with the lowest cost.
    If no waypoints found, returns null */
    public Waypoint getMinOpenWaypoint()
    {
        if (numOpenWaypoints() == 0)
        {
            return null;
        }
        Set<Location> openHeightsKeys = openPeaks.keySet();
        Iterator iter = openHeightsKeys.iterator();
        Waypoint best = null;
        float bestCost = 9999999;

        while (iter.hasNext())
        {
            Location location = (Location)iter.next();
            Waypoint waypoint = openPeaks.get(location);
            float waypointTotalCost = waypoint.getTotalCost();
            if (waypointTotalCost < bestCost)
            {
                best = openPeaks.get(location);
                bestCost = waypointTotalCost;
            }
        }
        return best;
    }

    public boolean addOpenWaypoint(Waypoint newWayPoint)
    {
        Location location = newWayPoint.getLocation();
        if (openPeaks.containsKey(location))
        {
            Waypoint tekWaypoint = openPeaks.get(location);
            if (newWayPoint.getPreviousCost() < tekWaypoint.getPreviousCost())
            {
                openPeaks.put(location, newWayPoint);
                return true;
            }
            return false;
        }
        openPeaks.put(location, newWayPoint);
        return true;
    }

    public int numOpenWaypoints()
    {
        return openPeaks.size();
    }

    public boolean isLocationClosed(Location loc)
    {
        return closePeaks.containsKey(loc);
    }

    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = openPeaks.remove(loc);
        closePeaks.put(loc, waypoint);
    }
}