package com.example.ania.fitapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TrainAlone extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_alone);
    }

    public void Back(View view)
    {
        finish();
    }

    public void Ok(View v) {

        int calories = 0;
        int time = 0;
        EditText caloriesText = (EditText) findViewById(R.id.calories_text);
        EditText timeText = (EditText) findViewById(R.id.time_text);

        try {
            calories = Integer.parseInt(caloriesText.getText().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        try {
            time = Integer.parseInt(timeText.getText().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        
        if(calories>7000 || calories < 100){
            Toast.makeText(this, " Change the number of calories", Toast.LENGTH_SHORT).show();
        }

        if(time>200 || time < 10){
            Toast.makeText(this, " Change the time", Toast.LENGTH_SHORT).show();
        }


    }

    public void showExercises(View view)
    {
        Intent i = new Intent(this, Exercises.class);
        startActivity(i);
    }

}

