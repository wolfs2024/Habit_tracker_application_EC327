package com.example.testing2;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

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
    ProgressBar progressBar;
    Timer timer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar); // Move this line here

        card1 = findViewById(R.id.water);
        card2 = findViewById(R.id.food);
        card3 = findViewById(R.id.exercise);
        card4 = findViewById(R.id.study);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WaterActivity.class));
                isCard1Clicked = true;
                progressBar.incrementProgressBy(25);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FoodActivity.class));
                isCard2Clicked = true;
                progressBar.incrementProgressBy(25);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));
                isCard3Clicked = true;
                progressBar.incrementProgressBy(25);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudyActivity.class));
                isCard4Clicked = true;
                progressBar.incrementProgressBy(25);
            }
        });

        timer = new Timer();
        timer.schedule(new UpdateProgressBar(), 0, 1000); // Update progress bar every second

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Cancel the timer when activity is paused
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new UpdateProgressBar(), 0, 1000);
        }
    }


    public void updateProgressBarValue(int value) {
        progressBar.setProgress(value);
    }

    class UpdateProgressBar extends TimerTask {

        long startTime = new Date().getTime();
        long duration = 5 * 60 * 1000; // 5 minutes in milliseconds

        public void run() {
            long currentTime = new Date().getTime();
            long elapsedTime = currentTime - startTime;
            long remainingTime = duration - elapsedTime;

            value = (int) ((remainingTime * 100) / duration);

            // Call method to update progress bar value
            updateProgressBarValue(value);
        }
    }
}
