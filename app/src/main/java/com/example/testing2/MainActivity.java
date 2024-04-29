package com.example.testing2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Declaring CardView variables
    public CardView card1, card2, card3, card4;
    private int value = 0;
    private boolean isCard1Clicked, isCard2Clicked, isCard3Clicked, isCard4Clicked;
    private int additionalProgress = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.water);
        card2 = findViewById(R.id.food);
        card3 = findViewById(R.id.exercise);
        card4 = findViewById(R.id.study);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WaterActivity.class));
                isCard1Clicked = true;
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FoodActivity.class));
                isCard2Clicked = true;
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
                isCard3Clicked = true;
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyActivity.class));
                isCard4Clicked = true;
            }
        });

        // Restore progress state if available
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int savedValue = preferences.getInt("ProgressBarStatus",0);
        updateProgressBarValue(savedValue);

        Timer timer = new Timer();
        timer.schedule(new UpdateProgressBar(), 0, 1000); // Update progress bar every second
    }

    // Method to update the progress bar value
    public void updateProgressBarValue(int value) {
        // Get reference to the progress bar
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(value);
    }

    class UpdateProgressBar extends TimerTask {

        long startTime = new Date().getTime();
        long duration = 5 * 60 * 1000; // 5 minutes in milliseconds

        public void run() {
            long currentTime = new Date().getTime();
            long elapsedTime = currentTime - startTime;
            long remainingTime = duration - elapsedTime;

            if (isCard1Clicked || isCard2Clicked || isCard3Clicked || isCard4Clicked) {
                additionalProgress += 25;
                isCard1Clicked = false;
                isCard2Clicked = false;
                isCard3Clicked = false;
                isCard4Clicked = false;
            }

            value = (int) ((remainingTime * 100) / duration) + additionalProgress;

            // Call method to update progress bar value
            updateProgressBarValue(value);
            additionalProgress = 0;
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("ProgressBarStatus",value);
    }
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int savedValue = savedInstanceState.getInt("ProgressBarStatus");
        updateProgressBarValue(savedValue);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Save the current progress value
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putInt("ProgressBarStatus", value).apply();
    }

}