package com.example.ania.fitapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TrainAlone extends AppCompatActivity {

    static boolean CaloriesChecked;
    static boolean TimeChecked;
    static int calories;
    static int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_alone);
    }

    public void Back(View view)
    {
        finish();
    }

    public void Choice(View v) {

        TextView questionText = (TextView) findViewById(R.id.question_text);

        CheckBox CaloriesCheckBox = (CheckBox) findViewById(R.id.calories_checkbox);
        CaloriesChecked = CaloriesCheckBox.isChecked();

        CheckBox TimeCheckBox = (CheckBox) findViewById(R.id.time_checkbox);
        TimeChecked = TimeCheckBox.isChecked();

        if(CaloriesChecked == true){
            questionText.setText("How many calories do you want to burn? [kcal]");
        }
        if(TimeChecked == true) {
            questionText.setText("How much time do you have? [min]");
        }
        if((TimeChecked == true && CaloriesChecked == true) || (TimeChecked == false && CaloriesChecked == false)) {
            questionText.setText("You can choose only one option");
            CaloriesCheckBox.setChecked(false);
            TimeCheckBox.setChecked(false);
        }
    }

    public void Ok(View v){

        EditText numberText = (EditText) findViewById(R.id.number_text);
        Intent i = new Intent(this, Exercises.class);

        if(CaloriesChecked == true && TimeChecked == false) {
            try {
                calories = Integer.parseInt(numberText.getText().toString());
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
            if (calories > 7000 || calories < 100) {
                Toast.makeText(this, " Change the number of calories", Toast.LENGTH_SHORT).show();
            }
            else{
                startActivity(i);
            }
        }

        if(TimeChecked == true && CaloriesChecked == false) {
            try {
                time = Integer.parseInt(numberText.getText().toString());
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
            if(time>200 || time < 10){
                Toast.makeText(this, " Change the time", Toast.LENGTH_SHORT).show();
            }
            else{
                startActivity(i);
            }
        }
        if((TimeChecked == false && CaloriesChecked == false) || (TimeChecked == true && CaloriesChecked == true)) {
            numberText.setText("");
            Toast.makeText(this, " First choose an option", Toast.LENGTH_SHORT).show();
        }

        if(numberText.getText().toString().matches("") ){
            calories = 0;
            time = 0;
            return;
        }
    }

}

