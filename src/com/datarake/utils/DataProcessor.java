package com.datarake.utils;

import java.util.*;
import java.util.stream.Collectors;

public class DataProcessor
{
    private ArrayList<DataLine> dataLines = null;
    DataProcessor(ArrayList<DataLine> _dataLines)
    {
        this.dataLines = _dataLines;
    }

    public Double percentOfMismatchedUser()
    {
        return this.dataLines.stream().filter( d -> !d.isMatchingUser()).count() / Double.valueOf(this.dataLines.size());
    }
    public Double percentOfMismatchedUserAndEmotion()
    {
        return this.dataLines.stream().filter( d -> !d.isMatching()).count() / Double.valueOf(this.dataLines.size());
    }

    public Integer indexOfFirstMismatchedUser()
    {
        return this.dataLines.indexOf(this.dataLines.stream().filter(d -> !d.isMatchingUser()).findFirst().get()) +1;
    }

    public Integer indexOfFirstMismatchedEmotion()
    {
        return this.dataLines.indexOf(this.dataLines.stream().filter(d -> !d.isMatchingEmotion()).findFirst().get()) + 1;
    }

    public Integer indexOfFirstMismatched()
    {
        return this.dataLines.indexOf(this.dataLines.stream().filter(d -> !d.isMatching()).findFirst().get()) + 1;
    }

    public Double distanceOfFirstMismatchedUser()
    {
        return this.dataLines.stream().filter(d -> !d.isMatchingUser()).findFirst().get().getDistance();
    }

    public Double distanceOfFirstMismatchedEmotion()
    {
        return this.dataLines.stream().filter(d -> !d.isMatchingEmotion()).findFirst().get().getDistance();
    }

    public Double distanceOfFirstMismatched()
    {
        return this.dataLines.stream().filter(d -> !d.isMatching()).findFirst().get().getDistance();
    }

    public Map<String, Boolean> absoluteMatch()
    {
        Map<String, List<DataLine>> tempMap = this.dataLines.stream().collect(Collectors.groupingBy(DataLine::getUserEmotionPair));
        Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
        for (String user  : tempMap.keySet())
        {
            List<DataLine> currList = tempMap.get(user);
            currList.sort(Comparator.comparing(DataLine::getDistance));
            if(currList.get(0).getEvalUserEmotionPair().equals(user))
            {
                resultMap.put(user,Boolean.TRUE);
            }
            else
            {
                resultMap.put(user,Boolean.FALSE);
            }

        }
        return resultMap;
    }

    public List<DataLine> getMatchedDistances()
    {
        Map<String, List<DataLine>> tempMap = this.dataLines.stream().collect(Collectors.groupingBy(DataLine::getUserEmotionPair));
        List<DataLine> result = new ArrayList<DataLine>();
        for (String user  : tempMap.keySet())
        {
            List<DataLine> currList = tempMap.get(user);
            currList.sort(Comparator.comparing(DataLine::getDistance));
            for(DataLine ds : currList)
            {
                if(ds.getEvalUserEmotionPair().equals(user))
                {
                    result.add(ds);
                }
            }
        }
        return result;
    }
}
