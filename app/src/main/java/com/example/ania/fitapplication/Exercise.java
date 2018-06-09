package com.example.ania.fitapplication;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.util.Calendar;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

public class Exercise extends AppCompatActivity {




    //Timer variable
    static TextView timeCounterText;
    private Button timeButton;
    private CountDownTimer countDownTimer;
    public long timeLeft;
    private boolean timeRunning;
    public long result;

    int minutes;
    int seconds;


    //File variable
    public Button saveButton;
    static TextView sportText;
    static TextView caloriesText;

    int realcalories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);




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



        saveButton = findViewById(R.id.saveButton);
        sportText = findViewById(R.id.textViewOfSport);
        caloriesText = findViewById(R.id.textViewOfCalories);

     /*   saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (timeRunning) {
                    startStop();
                }
                saveAsFile(sportText, caloriesText, timeCounterText);

            }
        });
*/

if (timeRunning==true)
    stopTimer();

            if (Exercises.gowalk == true) {

                //Creating a gif
                com.example.ania.fitapplication.GifImageView gifImageView = findViewById(R.id.GifImageView);
                gifImageView.setGifImageResource(R.drawable.walking);

                sportText.setText("Walking");
                caloriesText.setText("" + Exercises.TextCaloriesWalk);

                if (TrainAlone.CaloriesChecked == true) {
                    timeLeft = Exercises.walkTime * 60000;
                 result = Exercises.walkTime;
                }
                else {
                    timeLeft = TrainAlone.time * 60000;
                   result = TrainAlone.time;
                }

                Exercises.gowalk = false;
                updateTimer();



            } else if (Exercises.gorun == true) {

                //Creating a gif
                com.example.ania.fitapplication.GifImageView gifImageView = findViewById(R.id.GifImageView);
                gifImageView.setGifImageResource(R.drawable.running);


                sportText.setText("Running");
                caloriesText.setText("" + Exercises.TextCaloriesRun);

                if (TrainAlone.CaloriesChecked == true) {
                    timeLeft = Exercises.runtTime * 60000;
                 result = Exercises.runtTime;
                }
                else {
                    timeLeft = TrainAlone.time * 60000;
                 result = TrainAlone.time;
                }

                Exercises.gorun = false;
                updateTimer();



            } else if (Exercises.gobike == true) {

                //Creating a gif
                com.example.ania.fitapplication.GifImageView gifImageView = findViewById(R.id.GifImageView);
                gifImageView.setGifImageResource(R.drawable.bike);

                sportText.setText("Bike");
                caloriesText.setText("" + Exercises.TextCaloriesBike);

                if (TrainAlone.CaloriesChecked == true) {
                    timeLeft = Exercises.bikeTime * 60000;
                  result = Exercises.bikeTime;
                }
                else {
                    timeLeft = TrainAlone.time * 60000;
                  result = TrainAlone.time;
                }

                Exercises.gobike = false;
                updateTimer();


            }
        }

        public void onBackPressed() {

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


        minutes = (int) (timeLeft / 60000);
        seconds = (int) timeLeft % 60000 / 1000;

        String timeLeftText;

        timeLeftText = " " + minutes;
        timeLeftText += ":";
        if (seconds < 10)
            timeLeftText += "0";
        timeLeftText += seconds;

       timeCounterText.setText(timeLeftText);
        String p;
        p = timeCounterText.getText().toString();
       if (timeLeftText == "0:01")
           timeCounterText.setText("Well done! \n Save your work!");

    }
    //End of methods connected to timer

    public void RealCalories(int realtime){

            if (Exercises.gowalk == false)
                realcalories = (realtime * 240)/60;
            if (Exercises.gorun == false)
                realcalories = (realtime*735)/60;
            if (Exercises.gobike == false)
                realcalories = (realtime*550)/60;
    }
    //Creating a file
    public void Save(View view) {

        int finaltime = (int) (result*60 - minutes*60 - seconds)/60;
        RealCalories(finaltime);

        if (timeRunning) {
            startStop();
        }
        saveAsFile(sportText, realcalories , timeCounterText);
            Toast.makeText(this, " Results saved", Toast.LENGTH_SHORT).show();
    }

    public void Back(View view) {
        if (timeRunning == true)
            stopTimer();
        finish();
        Exercises.gowalk = false;
        Exercises.gorun = false;
        Exercises.gobike = false;
    }


    private void saveAsFile(TextView sport, int calories, TextView time) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("resultsOfExercises.txt", Context.MODE_PRIVATE | Context.MODE_APPEND  ));

            int finalTime = (int) (result*60 - minutes*60 - seconds);

            int min = (finalTime / 60);
            int sec = finalTime % 60;
//doesn't work
           String p;
            p = timeCounterText.getText().toString();
            if (p == "0:01") {
                min = (int)result;
                sec = 0;
            }

            String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


            String text =  mydate + "\n\nSport:  " + sport.getText().toString() + "\n" + "Calories:  "+realcalories + " kcal" + "\nTime: " +  min + "min " + sec + "sec" + "\n" + "\n" + "\n";
            outputStreamWriter.append(text);
//              outputStreamWriter.write(text);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }




}
