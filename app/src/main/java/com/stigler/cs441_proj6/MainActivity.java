package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    Singleton single;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        single = Singleton.getInstance();
    }

    public void beginTGame(View view)
    {
        //
    }

    public void beginUTGame(View view)
    {
        setQuestions();
        Intent actAction = new Intent(this, QuestActivity.class);
        startActivity(actAction);
    }

    public void seeTLB(View view)
    {
        //
    }

    public void seeUTLB(View view)
    {
        Intent actAction = new Intent(this, UTLeaderboard.class);
        startActivity(actAction);
    }

    public void setQuestions()
    {
        single.questions.clear();
        single.totalCorrect = 0;
        Question raceWinner = new Question("Which driver has won an F1 race?", "podium", "Lando Norris", "Mick Schumacher", "George Russell", "Pierre Gasly", "Pierre Gasly");
        Question ferrariMonza= new Question ("Who was the last Ferrari driver to win at Monza?", "monza", "Kimi Raikkonen", "Charles Leclerc", "Michael Schumacher", "Sebastian Vettel", "Charles Leclerc");
        single.questions.add(raceWinner);
        single.questions.add(ferrariMonza);
    }
}