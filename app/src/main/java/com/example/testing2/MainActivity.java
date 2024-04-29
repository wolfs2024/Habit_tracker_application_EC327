package com.example.testing2;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.ProgressBar;

import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

   //Doing this to push commit
    public CardView card1, card2, card3, card4;
    private int value = 0;
    private int savedProgress = 0;
    private boolean isCard1Clicked, isCard2Clicked, isCard3Clicked, isCard4Clicked;
    private int additionalProgress = 0; // Variable to track additional progress from card clicks

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

        // Restore progress state if savedInstanceState is not null

        if (savedInstanceState != null) {
            savedProgress = savedInstanceState.getInt("progress", 0);
            updateProgressBarValue(savedProgress); // Update progress bar with restored progress
        }
        Timer timer = new Timer();
        timer.schedule(new UpdateProgressBar(), 0, 1000); // Update progress bar every second
    }

    public void updateProgressBarValue(int value) {
        ProgressBar progressBar = findViewById(R.id.progressBar); // Get reference to the progress bar
        progressBar.setProgress(value);
    }

    class UpdateProgressBar extends TimerTask {

        long startTime = new Date().getTime();
        long duration = 5 * 60 * 1000; // 5 minutes in milliseconds

        public void run() {
            long currentTime = new Date().getTime();
            long elapsedTime = currentTime - startTime;
            long remainingTime = duration - elapsedTime;

            if (isCard1Clicked) {
                additionalProgress += 25;
                isCard1Clicked = false;
            } else if (isCard2Clicked) {
                additionalProgress += 25;
                isCard2Clicked = false;
            } else if (isCard3Clicked) {
                additionalProgress += 25;
                isCard3Clicked = false;
            } else if (isCard4Clicked) {
                additionalProgress += 25;
                isCard4Clicked = false;
            }

            value = (int) ((remainingTime * 100) / duration) + additionalProgress;

            // Call method to update progress bar value
            updateProgressBarValue(value);
            additionalProgress = 0;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("progress", value); // Save the current progress value
    }
}
