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

import java.util.ArrayList;

public class quizResults extends AppCompatActivity
{
    TextView outOfTen;
    Singleton single;
    Button return2main;
    EditText whatName;
    TableLayout UTTable;
    ArrayList<TableRow> rowList;
    int percentRes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        single = Singleton.getInstance();
        outOfTen = (TextView) findViewById(R.id.out10);
        return2main = (Button) findViewById(R.id.return2main);
        whatName = (EditText) findViewById(R.id.nameBox);
        UTTable = (TableLayout) findViewById(R.id.UTLeaders);

        percentRes = (int) (single.totalCorrect/single.questions.size())*100; //total correct is a double
        outOfTen.setText("Score: " + percentRes + "%");
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
        TableRow lbRow = new TableRow(this);
        TextView playerName = new TextView(this);
        TextView playerScore = new TextView(this);
        playerName.setText(pName);
        playerScore.setText(percentRes + "%");
        playerName.setTextSize(15);
        playerName.setTextColor(Color.BLACK);
        playerScore.setTextSize(15);
        playerScore.setTextColor(Color.BLACK);
        lbRow.addView(playerName, 0);
        lbRow.addView(playerScore, 1);
        rowList.add(lbRow);
        whatName.setText("");

        //need to sort the list somehow
    }
}