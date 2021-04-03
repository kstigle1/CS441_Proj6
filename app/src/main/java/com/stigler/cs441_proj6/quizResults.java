package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class quizResults extends AppCompatActivity
{
    TextView outOfTen, timeTaken;
    Singleton single;
    Button return2main;
    EditText whatName;
    int percentRes;
    String timeRes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        single = Singleton.getInstance();
        outOfTen = (TextView) findViewById(R.id.out10);
        return2main = (Button) findViewById(R.id.return2main);
        whatName = (EditText) findViewById(R.id.nameBox);
        timeTaken = (TextView) findViewById(R.id.timeTaken);

        percentRes = (int) (single.totalCorrect/single.questions.size()*100); //total correct is a double
        outOfTen.setText("Score: " + percentRes + "%");

        DecimalFormat timeFormat = new DecimalFormat("##.00");
        timeRes = timeFormat.format((single.endTime - single.startTime)/1000000000.0);
        if (single.timed)
        {
            timeTaken.setText("Total time: " + timeRes + " seconds");
        }
        else
        {
            timeTaken.setText("This quiz was not timed!!");
        }
    }

    public void returnToMain(View view)
    {
        addLeader();
        Intent actAction = new Intent(this, MainActivity.class);
        startActivity(actAction);
    }

    public void returnLeader(View view)
    {
        addLeader();
        Intent actAction = new Intent(this, UTLeaderboard.class);
        startActivity(actAction);
    }

    public void addLeader()
    {
        String pName = whatName.getText().toString();
        if (pName.equals(""))
        {
            pName = "Anonymous Player";
        }
        LBEntry entry = new LBEntry(pName, Integer.toString(percentRes), timeRes);
        single.LBEntries.add(entry);
        whatName.setText("");
    }
}