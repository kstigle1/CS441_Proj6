package com.stigler.cs441_proj6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Insts extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insts);
    }

    public void returnToMain(View view)
    {
        Intent actAction = new Intent(this, MainActivity.class);
        startActivity(actAction);
    }
}