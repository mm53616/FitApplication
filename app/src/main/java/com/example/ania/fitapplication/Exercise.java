package com.example.ania.fitapplication;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class Exercise extends AppCompatActivity {




//Timer variable
    static TextView timeCounterText;
    private Button timeButton;
    private CountDownTimer countDownTimer;
    private long timeLeft = TrainAlone.time*60000;
    private boolean timeRunning;

//File variable
    private Button saveButton;
    static TextView sportText;
    static TextView caloriesText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);


//Labels
//        sportText.setText(Exercises.a);

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
        caloriesText = findViewById(R.id.textViewOfCalories);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (timeRunning)
                    startStop();
                saveAsFile(sportText, caloriesText, timeCounterText );
            }
        });

        //Checking which button was clicked
        if(Exercises.gowalk == true){
            sportText.setText("Walking");
            caloriesText.setText(""+Exercises.TextCaloriesWalk);

          /*  if(TrainAlone.CaloriesChecked == true){
                timeLeft = Exercises.walkTime;
            }
            if(TrainAlone.TimeChecked == true){
                timeLeft = TrainAlone.time;
            }*/
        }
        else if(Exercises.gorun == true){
            sportText.setText("Running");
            caloriesText.setText(""+Exercises.TextCaloriesRun);
        }
        else if(Exercises.gobike == true){
            sportText.setText("Bike");
            caloriesText.setText(""+Exercises.TextCaloriesBike);
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
        Exercises.gowalk = false;
        Exercises.gorun = false;
        Exercises.gobike = false;
    }
    //End of methods connected to timer

    private void saveAsFile(TextView sport, TextView amountOfCalories, TextView time) {
        try {
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("resultsOfExercises.txt", Context.MODE_PRIVATE));

 //        Writer outputStreamWriter = new BufferedWriter(new OutputStreamWriter(
   //                 new FileOutputStream("resultsOfExercises.txt", true), "UTF-8"));

    //        PrintWriter outputStreamWriter = new PrintWriter(openFileOutput("resultsOfExercises.txt", Context.MODE_PRIVATE));


           String text = "Sport:  "+ sport.getText().toString() +  "\nBurnt calories: "  + amountOfCalories.getText().toString()  + "\nTime: " + time.getText().toString() + "\n" + "\n";
            outputStreamWriter.append(text);
//              outputStreamWriter.write(text);
                outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


}
