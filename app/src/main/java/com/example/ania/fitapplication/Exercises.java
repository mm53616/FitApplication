package com.example.ania.fitapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Exercises extends AppCompatActivity {

    static int walkTime;
    static int runtTime;
    static int bikeTime;
    static int walkCalories;
    static int runCalories;
    static int bikeCalories;

//    static String a="hhhhhhhhh";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

      final TextView WalkTime = (TextView) findViewById(R.id.editText_walk_time);
      final   TextView WalkCalories = (TextView) findViewById(R.id.editText_walk_calories);
        final TextView RunTime = (TextView) findViewById(R.id.editText_run_time);
        final TextView RunCalories = (TextView) findViewById(R.id.editText_run_calories);
        final TextView BikTime = (TextView) findViewById(R.id.editText_bike_time);
        final TextView BikeCalories = (TextView) findViewById(R.id.editText_bike_calories);
         Button walkButton = findViewById(R.id.image_walk);
          Button runButton = findViewById(R.id.image_run);
         Button bikeButton =  findViewById(R.id.image_bike);


//Choosing activity

        walkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        //      choosingWalking( WalkTime, WalkCalories);

           //     Exercise.sportText.setText("Walking");
       //         Exercise.caloriesText.setText(WalkCalories.getText());
      //          Exercise.timeCounterText.setText(WalkTime.getText());;
               Go( view);

            }
        });

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //    choosingRunning();

                Go( view);


            }
        });

        bikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

   //          choosingBike();
                Go( view);


            }
        });




//Counting calories and time
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
    }


    //Choosing activity: walking, running, riding a bike

    private void choosingWalking(TextView WalkTime, TextView WalkCalories) {


     Exercise.sportText.setText("Walking");
     Exercise.caloriesText.setText(WalkCalories.getText());
     Exercise.timeCounterText.setText(WalkTime.getText());

    }
    /**
    private void choosingRunning() {

     Exercise.sportText.setText("Running");
     Exercise.caloriesText.setText(RunCalories.getText());
     Exercise.timeCounterText.setText(RunTime.getText());

    }

    private void choosingBike() {

     Exercise.sportText.setText("Riding a bike");
     Exercise.caloriesText.setText(BikeCalories.getText());
     Exercise.timeCounterText.setText(BikTime.getText());

    }

**/



    public void Go(View view){
        Intent i = new Intent(this, Exercise.class);
        startActivity(i);
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






}
