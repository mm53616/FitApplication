package com.example.ania.fitapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {   ////#607D8B

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openTraining(View view){
        Intent i = new Intent(this, TrainAlone.class);
        startActivity(i);
    }

    public void Results(View view){
        Intent i = new Intent(this, Results.class);
        startActivity(i);
    }

    public void TrainTogether(View view){
        Intent i = new Intent(this, TrainTogether.class);
        startActivity(i);
    }




    public void Exit(View view){
        finish();
    }




}
