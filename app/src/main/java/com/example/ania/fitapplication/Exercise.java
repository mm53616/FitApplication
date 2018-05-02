package com.example.ania.fitapplication;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Exercise extends AppCompatActivity {

//Timer variable
    private TextView timeCounterText;
    private Button timeButton;
    private CountDownTimer countDownTimer;
    private long timeLeft = 600000; //10min
    private boolean timeRunning;

//File variable
    private Button saveButton;
    private TextView sportText;
    private TextView lengthText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

//Creating a gif
        com.example.ania.fitapplication.GifImageView gifImageView = findViewById(R.id.GifImageView);
        gifImageView.setGifImageResource(R.drawable.walking);

 //Creating a timer
        timeCounterText = findViewById(R.id.timerText);
        timeButton = findViewById(R.id.timerStart);

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startStop();
            }
        });
        updateTimer();

//Creating a file
        saveButton = findViewById(R.id.saveButton);
        sportText = findViewById(R.id.textViewOfSport);
        lengthText = findViewById(R.id.textViewOfTime);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveAsFile(sportText, lengthText);
            }
        });

    }


//Methods connected to timer
private void saveAsFile(TextView sport, TextView timeOfExercise) {
    try {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("resultsOfExercises.txt", Context.MODE_PRIVATE));
        String text = "Sport: "+ sport.getText().toString() +  "\n Burnt calories: 0"  + "\n Time: " + timeOfExercise.getText().toString();
        outputStreamWriter.write(text);
        outputStreamWriter.close();
    }
    catch (IOException e) {
        Log.e("Exception", "File write failed: " + e.toString());
    }
}


    //Methods connected to timer
    public void startStop() {

        if (timeRunning) {
            stopTimer();
        } else
            startTimer();
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimer();
            }

            public void onFinish() {

            }
        }.start();

        timeButton.setText("PAUSE");
        timeRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timeButton.setText("START");
        timeRunning = false;

    }

    private void updateTimer() {

        int minutes = (int) (timeLeft / 60000);
        int seconds = (int) timeLeft % 60000 / 1000;

        String timeLeftText;

        timeLeftText = " " + minutes;
        timeLeftText += ":";
        if (seconds < 10)
            timeLeftText += "0";
        timeLeftText += seconds;

        timeCounterText.setText(timeLeftText);
    }

    public void Back(View view) {
        finish();
    }
    //End of methods connected to timer


}
