package com.stigler.cs441_proj6;

import android.widget.Button;

public class Question
{
    String qText, image;
    String ans1, ans2, ans3, ans4;
    String correct;

    public Question(String text, String img, String a1, String a2, String a3, String a4, String corr)
    {
        qText = text;
        image = img;
        ans1 = a1;
        ans2 = a2;
        ans3 = a3;
        ans4 = a4;
        correct = corr;
    }
}
