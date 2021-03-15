package com.company;

import java.net.*;
import java.util.*;
import java.io.*;

public class Crawler
{
    public static void main(String[] args)
    {
        int depth = 0;

        LinkedList<URLDepthPair> futURLs = new LinkedList<>();
        LinkedList<URLDepthPair> presURLs = new LinkedList<>();

        URLDepthPair currentDepthPair = new URLDepthPair("https://www.google.com/", 0);
        futURLs.add(currentDepthPair);
        depth = 3;
        ArrayList<String> pastURLs = new ArrayList<>();
        pastURLs.add(currentDepthPair.getURL());

        while (futURLs.size() != 0)
        {
            URLDepthPair depthPair = futURLs.pop();
            presURLs.add(depthPair);
            int pendingURLDepth = depthPair.getDepth();
            LinkedList<String> listOfLinks;
            listOfLinks = Crawler.getAllLinks(depthPair);
            if (pendingURLDepth < depth)
            {
                for (String newURL : listOfLinks)
                {
                    if (!pastURLs.contains(newURL))
                    {
                        URLDepthPair newDepthPair = new URLDepthPair(newURL, pendingURLDepth + 1);
                        futURLs.add(newDepthPair);
                        pastURLs.add(newURL);
                    }
                }
            }
        }
        presURLs.stream().distinct().forEach(System.out::println);
    }

    private static LinkedList<String> getAllLinks(URLDepthPair myDepthPair)
    {
        LinkedList<String> URLs = new LinkedList<>();
        Socket socket;
        try
        {
            socket = new Socket(myDepthPair.getWebHost(), 80);
        }
        catch (UnknownHostException err1)
        {
            System.err.println("UnknownHostException:\n\t" + err1.getMessage());
            return URLs;
        }
        catch (IOException err2)
        {
            System.err.println("IOException:\n\t" + err2.getMessage());
            return URLs;
        }
        try
        {
            socket.setSoTimeout(3000);
        }
        catch (SocketException err3)
        {
            System.err.println("SocketException:\n\t" + err3.getMessage());
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
            System.err.println("IOException:\n\t" + err4.getMessage());
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
            System.err.println("IOException:\n\t" + err5.getMessage());
            return URLs;
        }
        InputStreamReader ISR = new InputStreamReader(IS);
        BufferedReader BR = new BufferedReader(ISR);
        while (true)
        {
            String str;
            try
            {
                str = BR.readLine();
            }
            catch (IOException err6)
            {
                System.err.println("IOException:\n\t" + err6.getMessage());
                return URLs;
            }
            if (str == null)
            {
                break;
            }
            int start;
            int end;
            int tek = 0;
            while (true)
            {
                String obr = "<a href=\"";
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
                if (tek == -1)
                {
                    break;
                }
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