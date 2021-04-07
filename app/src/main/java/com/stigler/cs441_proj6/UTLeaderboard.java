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



        updateUTFile(getApplicationContext());
        generateTable();
    }

    public void updateUTFile (Context context)
    {
        String newLine = "\n";
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("utFile.txt", Context.MODE_PRIVATE);
            for (int i=0; i<single.UTLBEntries.size(); i++)
            {
                String entryName = single.UTLBEntries.get(i).name + newLine;
                String entryScore = single.UTLBEntries.get(i).score + newLine;
                String entryTime = single.UTLBEntries.get(i).time + newLine;
                fileOutputStream.write(entryName.getBytes(Charset.forName("UTF-8")));
                fileOutputStream.write(entryScore.getBytes(Charset.forName("UTF-8")));
                fileOutputStream.write(entryTime.getBytes(Charset.forName("UTF-8")));
            }
        }
        catch (Exception e)
        {
            System.out.println("Something done messed up for utFile");
        }
    }

    public void returnToMain(View view)
    {
        Intent actAction = new Intent(this, MainActivity.class);
        startActivity(actAction);
    }

    public void clearFile(Context context)
    {
        String empty = null;
        try
        {
            FileOutputStream fileOutputStream = context.openFileOutput("utFile.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(empty.getBytes(Charset.forName("UTF-8")));

        }
        catch (Exception e)
        {
            System.out.println("Something done messed up for utFile");
        }
    }

    public void clearTable()
    {
        while (UTrow.size() > 0)
        {
            TableRow gone = UTrow.remove(UTrow.size()-1);
            UTtable.removeView(gone);
        }
        single.UTLBEntries.clear();
    }

    public void clearUTLeader(View view)
    {
        clearFile(getApplicationContext());
        clearTable();
        generateTable();
    }

    public void getFromUTFile(Context context)
    {
        clearTable();
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

    public void generateTable()
    {
        getFromUTFile(getApplicationContext());
        Collections.sort(single.UTLBEntries);
        for (int i=0; i<single.UTLBEntries.size(); i++)
        {
            TableRow tRow = new TableRow(this);
            TextView playerName = new TextView(this);
            TextView playerScore = new TextView(this);

            playerName.setText(single.UTLBEntries.get(i).name);
            playerName.setTextSize(15);
            playerName.setTextColor(Color.BLACK);
            tRow.addView(playerName, 0);

            playerScore.setText(single.UTLBEntries.get(i).score + "%");
            playerScore.setTextSize(15);
            playerScore.setTextColor(Color.BLACK);
            tRow.addView(playerScore, 1);

            UTtable.addView(tRow);
            UTrow.add(tRow);
        }
    }
}