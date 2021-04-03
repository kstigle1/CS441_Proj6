package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class UTLeaderboard extends AppCompatActivity
{
    TableLayout UTtable;
    ArrayList<TableRow> UTrow;
    Singleton single;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_t_leaderboard);
        single = Singleton.getInstance();
        UTtable = (TableLayout) findViewById(R.id.UTLeaders);
        UTrow = new ArrayList<>();

        generateTable();
    }

    public void returnToMain(View view)
    {
        Intent actAction = new Intent(this, MainActivity.class);
        startActivity(actAction);
    }

    public void clearTable(View view)
    {
        while (UTrow.size() > 0)
        {
            TableRow gone = UTrow.remove(UTrow.size()-1);
            UTtable.removeView(gone);
        }
        single.LBEntries.clear();
        generateTable();
    }

    public void generateTable()
    {
        Collections.sort(single.LBEntries);
        for (int i=0; i<single.LBEntries.size(); i++)
        {
            TableRow tRow = new TableRow(this);
            TextView playerName = new TextView(this);
            TextView playerScore = new TextView(this);

            playerName.setText(single.LBEntries.get(i).name);
            playerName.setTextSize(15);
            playerName.setTextColor(Color.BLACK);
            tRow.addView(playerName, 0);

            playerScore.setText(single.LBEntries.get(i).score + "%");
            playerScore.setTextSize(15);
            playerScore.setTextColor(Color.BLACK);
            tRow.addView(playerScore, 1);

            UTtable.addView(tRow);
            UTrow.add(tRow);
        }
    }
}