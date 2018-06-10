package com.example.ania.fitapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Results extends AppCompatActivity {

    TextView results;
    Button resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        results = findViewById(R.id.resultTextView);
        read();

        }


    public void read() {
        try {
            FileInputStream fileInputStream= openFileInput("resultsOfExercises.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines=bufferedReader.readLine())!=null) {
                stringBuffer.append(lines+"\n");
            }
            results.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Back(View view)
    {
        finish();
    }


}
