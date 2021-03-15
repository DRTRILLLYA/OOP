package lab8;

import java.net.*;
public class URLDepthPair
{
    private int depth;
    private String URL = "";

    URLDepthPair (String u, int d)
    {
        URL = u;
        depth = d;
    }
    URLDepthPair (String u)
    {
        URL = u;
        depth = 0;
    }

    public String getURL()
    {
        return URL;
    }
    public int getDepth()
    {
        return depth;
    }
    public String toString()
    {
        return URL + " " + depth;
    }

    public String getDocPath()
    {
        try
        {
            URL tempURL = new URL(URL);
            return tempURL.getPath();
        }
        catch (MalformedURLException MUE)
        {
            System.err.println("MalformedURLException in getDocPath(): " + MUE.getMessage());
            return null;
        }
    }

    public String getWebHost()
    {
        try
        {
            URL tekURL = new URL(URL);
            return tekURL.getHost();
        }
        catch (MalformedURLException MUE)
        {
            System.err.println("MalformedURLException in getWebHost: " + MUE.getMessage());
            return null;
        }
    }
}
