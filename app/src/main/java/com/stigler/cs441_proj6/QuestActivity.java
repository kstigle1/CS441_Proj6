package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestActivity extends AppCompatActivity
{

    Singleton single;
    Button answer1, answer2, answer3, answer4, nextQ;
    TextView questionTxt;
    int currentQ = 0;
    Question curr;

    ImageView activeImage;
    ImageView podium, monza, paulRicard, ukFlag, softtire, giohelmet, suzuka, worldmap, cota;

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

        podium = (ImageView) findViewById(R.id.podium);
        monza = (ImageView) findViewById(R.id.monza);
        paulRicard = (ImageView) findViewById(R.id.paulricard);
        ukFlag = (ImageView) findViewById(R.id.ukFlag);
        softtire = (ImageView) findViewById(R.id.softtire);
        giohelmet = (ImageView) findViewById(R.id.giohelmet);
        suzuka = (ImageView) findViewById(R.id.suzuka);
        worldmap = (ImageView) findViewById(R.id.worldmap);
        cota = (ImageView) findViewById(R.id.cota);

        activeImage = podium;

        shuffle();
        displayQ();
    }

    public void displayQ()
    {
        curr = single.questions.get(currentQ);
        activeImage.setAlpha((float) 0.0);
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

        switch (curr.image)
        {
            case "podium": podium.setAlpha((float) 1.0); activeImage = podium;
            break;
            case "monza": monza.setAlpha((float) 1.0); activeImage = monza;
            break;
            case "paulricard": paulRicard.setAlpha((float) 1.0); activeImage = paulRicard;
            break;
            case "ukFlag": ukFlag.setAlpha((float) 1.0); activeImage = ukFlag;
            break;
            case "softtire": softtire.setAlpha((float) 1.0); activeImage = softtire;
            break;
            case "giohelmet": giohelmet.setAlpha((float) 1.0); activeImage = giohelmet;
            break;
            case "suzuka": suzuka.setAlpha((float) 1.0); activeImage = suzuka;
            break;
            case "worldmap": worldmap.setAlpha((float) 1.0); activeImage = worldmap;
            break;
            case "cota": cota.setAlpha((float) 1.0); activeImage = cota;
            break;
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
            single.endTime = System.nanoTime();
            Intent actAction = new Intent(this, quizResults.class);
            startActivity(actAction);
        }
    }
}