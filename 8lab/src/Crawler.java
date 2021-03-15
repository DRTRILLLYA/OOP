 package lab8;

 import java.util.*;
 import java.io.*;
 import java.net.*;

public class Crawler
{
    static int numthreads = 10;
    static int timeout = 500;
    public static void main(String[] args)
    {

        URLDepthPair currentDepthPair = new URLDepthPair("https://www.google.com/");
        ArrayList<String> pastURLs = new ArrayList<String>();
        pastURLs.add(currentDepthPair.getURL());

        URLPool pool = new URLPool(2);
        pool.put(currentDepthPair);

        int activeAmount = Thread.activeCount();

        while (pool.getWaitThreads() != numthreads)
        {
            if (Thread.activeCount() - activeAmount < numthreads)
            {
                CrawlerTask crawler = new CrawlerTask(pool);
                new Thread(crawler).start();
            }
            else
            {
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException IE)
                {
                    System.out.println("InterruptedException");
                }
            }
        }
        for (String s : pool.pastURLs)
        {
            System.out.println(s);
        }
        System.exit(0);
    }

    public static LinkedList<String> getAllLinks(URLDepthPair myDepthPair)
    {
        LinkedList<String> URLs = new LinkedList<String>();
        Socket socket;
        try
        {
            socket = new Socket(myDepthPair.getWebHost(), 80);
        }
        catch (UnknownHostException err1)
        {
            System.err.println("UnknownHostException: " + err1.getMessage());
            return URLs;
        }
        catch (IOException err2)
        {
            System.err.println("IOException: " + err2.getMessage());
            return URLs;
        }
        try
        {
            socket.setSoTimeout(timeout);
        }
        catch (SocketException err3)
        {
            System.err.println("SocketException: " + err3.getMessage());
            return URLs;
        }

        String docPath = myDepthPair.getDocPath();
        String webHost = myDepthPair.getWebHost();

        OutputStream OS;

        try
        {
            OS = socket.getOutputStream();
        }
        catch (IOException err4)
        {
            System.err.println("IOException: " + err4.getMessage());
            return URLs;
        }

        PrintWriter PW = new PrintWriter(OS, true);
        PW.println("GET " + docPath + " HTTP/1.1");
        PW.println("Host: " + webHost);
        PW.println("Connection: close");
        PW.println();

        InputStream IS;
        try
        {
            IS = socket.getInputStream();
        }
        catch (IOException err5)
        {
            System.err.println("IOException: " + err5.getMessage());
            return URLs;
        }
        InputStreamReader inStreamReader = new InputStreamReader(IS);
        BufferedReader BuffReader = new BufferedReader(inStreamReader);

        while (true)
        {
            String str;
            try
            {
                str = BuffReader.readLine();
            }
            catch (IOException err6)
            {
                System.err.println("IOException: " + err6.getMessage());
                return URLs;
            }
            if (str == null)
            {
                break;
            }
            int start = 0;
            int end = 0;
            int tek = 0;

            while (true)
            {
                String obr = "a href=\"";
                String eobr = "\"";

                tek = str.indexOf(obr, tek);
                if (tek == -1)
                {
                    break;
                }
                tek += obr.length();
                start = tek;

                end = str.indexOf(eobr, tek);
                tek = end;

                String newstr = str.substring(start, end);

                if(newstr.startsWith("http"))
                {
                    URLs.add(newstr);
                }
                else if(!newstr.startsWith("tel"))
                {
                    if(newstr.startsWith("/"))
                    {
                        URLs.add("http://"+webHost+""+newstr);
                    }
                    else
                    {
                        URLs.add("http://"+webHost+"/"+newstr);
                    }
                }
            }

        }
        return URLs;
    }
}