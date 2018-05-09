package com.example.ania.fitapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Exercises extends AppCompatActivity {

    static long walkTime;
    static long runtTime;
    static long bikeTime;
    int walkCalories;
    int runCalories;
    int bikeCalories;


    static String TextTimeWalk;
    static String TextTimeRun;
    static String TextTimeBike;
    static String TextCaloriesWalk;
    static String TextCaloriesRun;
    static String TextCaloriesBike;

    static boolean gowalk = false;
    static boolean gorun = false;
    static boolean gobike = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        TextView WalkTime = (TextView) findViewById(R.id.editText_walk_time);
        TextView WalkCalories = (TextView) findViewById(R.id.editText_walk_calories);
        TextView RunTime = (TextView) findViewById(R.id.editText_run_time);
        TextView RunCalories = (TextView) findViewById(R.id.editText_run_calories);
        TextView BikTime = (TextView) findViewById(R.id.editText_bike_time);
        TextView BikeCalories = (TextView) findViewById(R.id.editText_bike_calories);

        if(TrainAlone.CaloriesChecked == true){

            calculateTime(TrainAlone.calories);

            WalkCalories.setText("Calories:  "+TrainAlone.calories + " kcal");
            RunCalories.setText("Calories:  "+TrainAlone.calories + " kcal");
            BikeCalories.setText("Calories:  "+TrainAlone.calories + " kcal");

            WalkTime.setText("Time:  "+ walkTime + " min");
            RunTime.setText("Time:  "+runtTime + " min");
            BikTime.setText("Time:  "+bikeTime + " min");
        }

        if(TrainAlone.TimeChecked == true){

            calculateCalories(TrainAlone.time);

            WalkCalories.setText("Calories:  "+walkCalories + " kcal");
            RunCalories.setText("Calories:  "+runCalories + " kcal");
            BikeCalories.setText("Calories:  "+bikeCalories + " kcal");

            WalkTime.setText("Time:  "+TrainAlone.time + " min");
            RunTime.setText("Time:  "+TrainAlone.time + " min");
            BikTime.setText("Time:  "+TrainAlone.time + " min");
        }
        // getting final time from TextView
        TextTimeWalk = WalkTime.getText().toString();
        TextTimeRun = RunTime.getText().toString();
        TextTimeBike = BikTime.getText().toString();
// getting final calories from TextView
        TextCaloriesWalk = WalkCalories.getText().toString();
        TextCaloriesRun = RunCalories.getText().toString();
        TextCaloriesBike = BikeCalories.getText().toString();
    }



    public void Back(View view)
    {
        finish();
    }


    public void calculateTime(int number){
        walkTime = (number*60)/240;
        runtTime = (number*60)/735;
        bikeTime = (number*60)/550;
    }

    public void calculateCalories(int number){
        walkCalories = (number*240)/60;
        runCalories = (number*735)/60;
        bikeCalories = (number*550)/60;
    }
    // functions for buttons
    public void GoWalk(View view){
        Intent i = new Intent(this, Exercise.class);
        startActivity(i);
        gowalk = true;
        gorun = false;
        gobike = false;
    }
    public void GoRun(View view){
        Intent i = new Intent(this, Exercise.class);
        startActivity(i);
        gorun = true;
        gowalk = false;
        gobike = false;
    }
    public void GoBike(View view){
        Intent i = new Intent(this, Exercise.class);
        startActivity(i);
        gobike = true;
        gowalk = false;
        gorun = false;
    }
}
