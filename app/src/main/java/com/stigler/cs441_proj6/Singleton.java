package com.stigler.cs441_proj6;

import java.util.ArrayList;

public class Singleton
{
    private static Singleton single = null;
    ArrayList<Question> questions = new ArrayList<>();
    int totalCorrect = 0;

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
