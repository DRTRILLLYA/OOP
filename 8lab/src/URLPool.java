package lab8;

import java.util.*;

public class URLPool
{
    public static int maxdepth;
    private LinkedList<URLDepthPair> futURLs;
    public LinkedList<URLDepthPair> presURLs;
    public ArrayList<String> pastURLs = new ArrayList<String>();

    public int waitingThreads;

    public URLPool(int d)
    {
        waitingThreads = 0;
        futURLs = new LinkedList<URLDepthPair>();
        presURLs = new LinkedList<URLDepthPair>();
        maxdepth = d;
    }

    public synchronized int getWaitThreads()
    {
        return waitingThreads;
    }

    public synchronized int size()
    {
        return futURLs.size();
    }

    public synchronized void decrimentWaitingThreads()
    {
        waitingThreads--;
    }

    public synchronized boolean put(URLDepthPair depthPair)
    {
        boolean isAdded = false;

        if (depthPair.getDepth() < maxdepth && !presURLs.contains(depthPair.getURL()))
        {
            futURLs.addLast(depthPair);
            isAdded = true;
            this.notify();
        }
        else
        {
            addSeenURL(depthPair);
        }
        return isAdded;
    }

    public synchronized URLDepthPair getPair()
    {
        URLDepthPair depthPair = null;
        if (futURLs.size() == 0)
        {
            waitingThreads++;
            try
            {
                this.wait();
            }
            catch (InterruptedException interruptedException)
            {
                System.err.println("InterruptedException: " + interruptedException.getMessage());
                return null;
            }
        }
        if (waitingThreads > 0)
        {
            waitingThreads--;
        }
        depthPair = futURLs.pop();
        addSeenURL(depthPair);
        presURLs.add(depthPair);
        return depthPair;
    }

    /**A method that add URLs to the list of seen*/
    private synchronized void addSeenURL(URLDepthPair dp)
    {
        if(!pastURLs.contains(dp.toString()))
        {
            pastURLs.add(dp.toString());
        }
    }

    public synchronized ArrayList<String> getSeenList()
    {
        return pastURLs;
    }
}