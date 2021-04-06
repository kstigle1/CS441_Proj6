package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class TLeaderboard extends AppCompatActivity
{
    TableLayout Ttable;
    ArrayList<TableRow> Trow;
    Singleton single;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_leaderboard);

        single = Singleton.getInstance();
        Ttable = (TableLayout) findViewById(R.id.TLeaders);
        Trow = new ArrayList<>();

        generateTable();
    }

    public void returnToMain(View view)
    {
        Intent actAction = new Intent(this, MainActivity.class);
        startActivity(actAction);
    }

    public void clearTable(View view)
    {
        while (Trow.size() > 0)
        {
            TableRow gone = Trow.remove(Trow.size()-1);
            Ttable.removeView(gone);
        }
        single.TLBEntries.clear();
        generateTable();
    }

    public void generateTable()
    {
        Collections.sort(single.TLBEntries);
        for (int i=0; i<single.TLBEntries.size(); i++)
        {
            TableRow tRow = new TableRow(this);
            TextView playerName = new TextView(this);
            TextView playerScore = new TextView(this);
            TextView playerTime = new TextView(this);

            playerName.setText(single.TLBEntries.get(i).name);
            playerName.setTextSize(15);
            playerName.setTextColor(Color.BLACK);
            tRow.addView(playerName, 0);

            playerScore.setText(single.TLBEntries.get(i).score + "%");
            playerScore.setTextSize(15);
            playerScore.setTextColor(Color.BLACK);
            tRow.addView(playerScore, 1);

            playerTime.setText(single.TLBEntries.get(i).time + " seconds");
            playerTime.setTextSize(15);
            playerTime.setTextColor(Color.BLACK);
            tRow.addView(playerTime, 2);

            Ttable.addView(tRow);
            Trow.add(tRow);
        }
    }
}