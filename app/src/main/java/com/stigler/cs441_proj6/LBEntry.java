package com.stigler.cs441_proj6;

public class LBEntry implements Comparable<LBEntry>
{
    String name, score, time;
    Singleton single = Singleton.getInstance();

    public LBEntry (String n, String s, String t)
    {
        name = n;
        score = s;
        time = t;
    }

    @Override
    public int compareTo(LBEntry otherEntry)
    {
        if (!score.equals(otherEntry.score))
        {
            return (Integer.parseInt(otherEntry.score) - Integer.parseInt(score));
        }
        else if (single.timed && !time.equals(otherEntry.time))
        {
            return (Integer.parseInt(time) - Integer.parseInt(otherEntry.time));
        }
        else
        {
            return name.compareToIgnoreCase(otherEntry.name);
        }
    }
}
