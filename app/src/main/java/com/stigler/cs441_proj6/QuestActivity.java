package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Random;

public class QuestActivity extends AppCompatActivity
{

    Singleton single;
    Button answer1, answer2, answer3, answer4, nextQ;
    TextView questionTxt;
    int currentQ = 0;
    Question curr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        single = Singleton.getInstance();
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        nextQ = (Button) findViewById(R.id.nextQ);
        questionTxt = (TextView) findViewById(R.id.questionText);

        shuffle();
        displayQ();
    }

    public void displayQ()
    {
        curr = single.questions.get(currentQ);
        nextQ.setAlpha((float) 0.0);
        nextQ.setEnabled(false);
        if (currentQ != single.questions.size()-1)
        {
            nextQ.setText("Next Question");
        }
        else
        {
            nextQ.setText("Finish Quiz");
        }

        questionTxt.setText(curr.qText);

        answer1.setBackgroundColor(Color.BLUE);
        answer1.setTextColor(Color.WHITE);
        answer1.setText(curr.ans1);
        answer1.setEnabled(true);

        answer2.setBackgroundColor(Color.BLUE);
        answer2.setTextColor(Color.WHITE);
        answer2.setText(curr.ans2);
        answer2.setEnabled(true);

        answer3.setBackgroundColor(Color.BLUE);
        answer3.setTextColor(Color.WHITE);
        answer3.setText(curr.ans3);
        answer3.setEnabled(true);

        answer4.setBackgroundColor(Color.BLUE);
        answer4.setTextColor(Color.WHITE);
        answer4.setText(curr.ans4);
        answer4.setEnabled(true);
    }

    public void shuffle()
    {
        for (int i=0; i<7; i++)
        {
            Collections.shuffle(single.questions);
        }
    }

    public void clickedAns1(View view)
    {
        if (answer1.getText() == curr.correct)
        {
            single.totalCorrect++;
        }
        resultColors();
    }

    public void clickedAns2(View view)
    {
        if (answer2.getText() == curr.correct)
        {
            single.totalCorrect++;
        }
        resultColors();
    }

    public void clickedAns3(View view)
    {
        if (answer3.getText() == curr.correct)
        {
            single.totalCorrect++;
        }
        resultColors();
    }

    public void clickedAns4(View view)
    {
        if (answer4.getText() == curr.correct)
        {
            single.totalCorrect++;
        }
        resultColors();
    }

    private void resultColors()
    {
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        answer4.setEnabled(false);
        nextQ.setEnabled(true);
        nextQ.setAlpha((float) 1.0);

       if (curr.correct == answer1.getText())
       {
           answer1.setBackgroundColor(Color.GREEN);
           answer1.setTextColor(Color.BLACK);
           answer2.setBackgroundColor(Color.RED);
           answer2.setTextColor(Color.BLACK);
           answer3.setBackgroundColor(Color.RED);
           answer3.setTextColor(Color.BLACK);
           answer4.setBackgroundColor(Color.RED);
           answer4.setTextColor(Color.BLACK);
       }
       else if (curr.correct == answer2.getText())
       {
           answer1.setBackgroundColor(Color.RED);
           answer1.setTextColor(Color.BLACK);
           answer2.setBackgroundColor(Color.GREEN);
           answer2.setTextColor(Color.BLACK);
           answer3.setBackgroundColor(Color.RED);
           answer3.setTextColor(Color.BLACK);
           answer4.setBackgroundColor(Color.RED);
           answer4.setTextColor(Color.BLACK);
       }
       else if (curr.correct == answer3.getText())
       {
           answer1.setBackgroundColor(Color.RED);
           answer1.setTextColor(Color.BLACK);
           answer2.setBackgroundColor(Color.RED);
           answer2.setTextColor(Color.BLACK);
           answer3.setBackgroundColor(Color.GREEN);
           answer3.setTextColor(Color.BLACK);
           answer4.setBackgroundColor(Color.RED);
           answer4.setTextColor(Color.BLACK);
       }
       else if (curr.correct == answer4.getText())
       {
           answer1.setBackgroundColor(Color.RED);
           answer1.setTextColor(Color.BLACK);
           answer2.setBackgroundColor(Color.RED);
           answer2.setTextColor(Color.BLACK);
           answer3.setBackgroundColor(Color.RED);
           answer3.setTextColor(Color.BLACK);
           answer4.setBackgroundColor(Color.GREEN);
           answer4.setTextColor(Color.BLACK);
       }
    }

    public void clickedNQ(View view)
    {
        if (nextQ.getText() == "Next Question")
        {
            currentQ++;
            displayQ();
        }
        else if (nextQ.getText() == "Finish Quiz")
        {
            Intent actAction = new Intent(this, quizResults.class); //will be changed to a results screen, scoreboard is temp
            startActivity(actAction);
        }
    }
}