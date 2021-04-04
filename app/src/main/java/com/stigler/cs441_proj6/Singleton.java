package com.stigler.cs441_proj6;

import android.content.Context;

import java.util.ArrayList;

public class Singleton
{
    private static Singleton single = null;
    ArrayList<Question> questions = new ArrayList<>();
    double totalCorrect = 0.0; //double instead of int so percent calculation works in results
    ArrayList<LBEntry> UTLBEntries = new ArrayList<>();
    ArrayList<LBEntry> TLBEntries = new ArrayList<>();

    boolean timed = false;
    long startTime, endTime;

    private Singleton()
    {
        //
    }

    public static Singleton getInstance()
    {
        if (Singleton.single == null)
        {
            Singleton.single = new Singleton();
        }
        return Singleton.single;
    }
}
