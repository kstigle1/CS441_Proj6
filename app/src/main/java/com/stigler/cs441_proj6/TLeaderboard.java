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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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

        updateTFile(getApplicationContext());
        generateTable();
    }

    public void returnToMain(View view)
    {
        Intent actAction = new Intent(this, MainActivity.class);
        startActivity(actAction);
    }

    public void clearTable()
    {
        while (Trow.size() > 0)
        {
            TableRow gone = Trow.remove(Trow.size()-1);
            Ttable.removeView(gone);
        }
        single.TLBEntries.clear();
    }

    public void clearTLeader(View view)
    {
        clearFile(getApplicationContext());
        clearTable();
        generateTable();
    }

    public void clearFile(Context context)
    {
        String empty = null;
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("tFile.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(empty.getBytes(Charset.forName("UTF-8")));

        }
        catch (Exception e)
        {
            System.out.println("Something done messed up for tFile");
        }
    }

    public void updateTFile (Context context)
    {
        String newLine = "\n";
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("tFile.txt", Context.MODE_PRIVATE);
            for (int i=0; i<single.TLBEntries.size(); i++)
            {
                String entryName = single.TLBEntries.get(i).name + newLine;
                String entryScore = single.TLBEntries.get(i).score + newLine;
                String entryTime = single.TLBEntries.get(i).time + newLine;
                fileOutputStream.write(entryName.getBytes(Charset.forName("UTF-8")));
                fileOutputStream.write(entryScore.getBytes(Charset.forName("UTF-8")));
                fileOutputStream.write(entryTime.getBytes(Charset.forName("UTF-8")));
            }
        }
        catch (Exception e)
        {
            System.out.println("Something done messed up for tFile");
        }
    }

    public void getFromTFile(Context context)
    {
        clearTable();
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

    public void generateTable()
    {
        getFromTFile(getApplicationContext());
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