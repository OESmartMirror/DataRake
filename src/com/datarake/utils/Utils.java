package com.datarake.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils
{
    public static ArrayList<DataLine> readCsv(File file)
    {
        ArrayList<DataLine> values = new ArrayList<DataLine>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] temp = line.split(";");
                values.add(new DataLine(temp[0],temp[1],temp[2]));
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File passed as argument is absent!");
        }
        catch (IOException e)
        {
            System.err.println("Error during file read!");
        }
        return values;
    }

    public static <K,V> String createMapString(Map<K,V> kvMap)
    {
        StringBuffer sb = new StringBuffer();
        for(K key : kvMap.keySet())
        {
            sb.append(key).append(" :").append(kvMap.get(key)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static <T> String createListString(List<T> tList)
    {
        StringBuffer sb = new StringBuffer();
        for (T listItem : tList)
        {
            sb.append(listItem.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
