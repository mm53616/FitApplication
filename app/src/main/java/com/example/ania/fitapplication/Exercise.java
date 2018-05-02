package com.example.ania.fitapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Exercise extends AppCompatActivity {

    private TextView timeCounterText;
    private Button timeButton;
    private CountDownTimer countDownTimer;
    private long timeLeft = 600000; //10min
    private boolean timeRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        com.example.ania.fitapplication.GifImageView gifImageView = (com.example.ania.fitapplication.GifImageView) findViewById(R.id.GifImageView);
        gifImageView.setGifImageResource(R.drawable.walking);

        timeCounterText = findViewById(R.id.timerText);
        timeButton = findViewById(R.id.timerStart);

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startStop();
            }
        });
        updateTimer();
        }




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
        int seconds = (int)timeLeft % 60000 / 1000;

        String timeLeftText;

        timeLeftText = " " + minutes;
        timeLeftText += ":";
        if (seconds < 10)
            timeLeftText += "0";
        timeLeftText += seconds;

        timeCounterText.setText(timeLeftText);


    }





 }
