package com.example.ania.fitapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openTraining(View view){
        Intent i = new Intent(this, TrainAlone.class);
        startActivity(i);
    }

    public void Ok(View view){
        Intent i = new Intent(this, Exercises.class);
        startActivity(i);
    }


    public void Exit(View view){
        finish();
    }




}
