package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class quizResults extends AppCompatActivity
{
    TextView outOfTen;
    Singleton single;
    Button return2main;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        single = Singleton.getInstance();
        outOfTen = (TextView) findViewById(R.id.out10);
        return2main = (Button) findViewById(R.id.return2main);

        int percentRes = (int) (single.totalCorrect/single.questions.size())*100; //total correct is a double
        outOfTen.setText("Score: " + percentRes + "%");
    }

    public void returnToMain(View view)
    {
        Intent actAction = new Intent(this, MainActivity.class);
        startActivity(actAction);
    }

    public void returnLeader(View view)
    {
        Intent actAction = new Intent(this, UTLeaderboard.class);
        startActivity(actAction);
    }
}