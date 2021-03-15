package lab8;
import java.util.*;

public class CrawlerTask implements Runnable
{
    public URLDepthPair depthPair;
    public URLPool Pool;
    public CrawlerTask(URLPool pool)
    {
        this.Pool = pool;
    }

    public void run()
    {
        depthPair = Pool.getPair();
        int myDepth = depthPair.getDepth();

        LinkedList<String> linksList = new LinkedList<String>();
        linksList = Crawler.getAllLinks(depthPair);

        for (String newURL : linksList)
        {
            URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);
            Pool.put(newDepthPair);
        }
    }
}