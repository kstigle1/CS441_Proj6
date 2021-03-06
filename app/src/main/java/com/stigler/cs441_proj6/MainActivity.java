package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity
{

    Singleton single;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        single = Singleton.getInstance();
        if (single.startUp)
        {
            fillTLB(getApplicationContext());
            fillUTLB(getApplicationContext());
            single.startUp = false;
        }
    }

    public void fillTLB(Context context)
    {
        try
        {
            FileInputStream fileInputStream = context.openFileInput("tFile.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null)
            {
                LBEntry temp = new LBEntry(line, reader.readLine(), reader.readLine());
                single.TLBEntries.add(temp);
                line = reader.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("Something done messed up for tFile");
        }
    }

    public void fillUTLB(Context context)
    {
        try
        {
            FileInputStream fileInputStream = context.openFileInput("utFile.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null)
            {
                LBEntry temp = new LBEntry(line, reader.readLine(), reader.readLine());
                single.UTLBEntries.add(temp);
                line = reader.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("Something done messed up for utFile");
        }
    }

    public void beginTGame(View view)
    {
        setQuestions();
        single.timed = true;
        single.startTime = System.nanoTime();
        Intent actAction = new Intent(this, QuestActivity.class);
        startActivity(actAction);
    }

    public void beginUTGame(View view)
    {
        setQuestions();
        single.timed = false;
        Intent actAction = new Intent(this, QuestActivity.class);
        startActivity(actAction);
    }

    public void seeTLB(View view)
    {
        Intent actAction = new Intent(this, TLeaderboard.class);
        startActivity(actAction);
    }

    public void seeUTLB(View view)
    {
        Intent actAction = new Intent(this, UTLeaderboard.class);
        startActivity(actAction);
    }

    public void seeInsts(View view)
    {
        Intent actAction = new Intent(this, Insts.class);
        startActivity(actAction);
    }

    public void setQuestions()
    {
        single.questions.clear();
        single.totalCorrect = 0;
        Question raceWinner = new Question("Which driver has won an F1 race?", "podium", "Lando Norris", "Mick Schumacher", "George Russell", "Pierre Gasly", "Pierre Gasly");
        Question ferrariMonza= new Question ("Who was the last Ferrari driver to win at Monza?", "monza", "Kimi Raikkonen", "Charles Leclerc", "Michael Schumacher", "Sebastian Vettel", "Charles Leclerc");
        Question whichTrack = new Question ("Which track is this?", "paulricard", "Circuit Paul Ricard", "Circuit Giles Villeneuve", "Circuit of the Americas", "Silverstone Circuit", "Circuit Paul Ricard");
        Question notUK = new Question("Which driver is not from the UK?", "ukFlag", "Lewis Hamilton", "Lance Stroll", "George Russell", "Lando Norris", "Lance Stroll");
        Question numTires = new Question("How many total tire types/compounds are there?", "softtire", "3", "5", "7", "9", "7");
        Question whichHelmet = new Question("Who's helmet is this?", "giohelmet", "Nikita Mazepin", "Yuki Tsunoda", "Antonio Giovinazzi", "Kimi Raikkonen", "Antonio Giovinazzi");
        Question figure8 = new Question("What is the only track that is a figure 8?", "suzuka", "Suzuka International Circuit", "Bahrain International Circuit", "Circuit de Spa-Francorchamps", "Marina Bay Street Circuit", "Suzuka International Circuit");
        Question notCountry = new Question("Which country has never hosted a Grand Prix?", "worldmap", "Hungary", "South Africa", "Italy", "New Zealand", "New Zealand");
        Question notTrack = new Question("Which US racetrack has never hosted a Grand Prix?", "cota", "Indianapolis Motor Speedway", "Circuit of the Americas", "Watkins Glen International", "Laguna Seca Raceway", "Laguna Seca Raceway");
        Question jewel = new Question("Which race is considered \"The Jewel in F1's Crown?\"", "pool", "British Grand Prix", "Abu Dhabi Grand Prix", "Monaco Grand Prix", "Italian Grand Prix", "Monaco Grand Prix");
        single.questions.add(raceWinner);
        single.questions.add(ferrariMonza);
        /*single.questions.add(whichTrack);
        single.questions.add(notUK);
        single.questions.add(numTires);
        single.questions.add(whichHelmet);
        single.questions.add(figure8);
        single.questions.add(notCountry);
        single.questions.add(notTrack);
        single.questions.add(jewel);*/
    }
}