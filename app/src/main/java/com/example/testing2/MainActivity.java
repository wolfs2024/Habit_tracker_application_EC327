package com.example.testing2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    // Create the classes for each card
    public CardView card1, card2, card3, card4;


    // testing testing

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = (CardView) findViewById(R.id.water);
        card2 = (CardView) findViewById(R.id.food);
        card3 = (CardView) findViewById(R.id.exercise);
        card4 = (CardView) findViewById(R.id.study);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, WaterActivity.class));

            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, FoodActivity.class));

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ExerciseActivity.class));

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, StudyActivity.class));

            }
        });
    }
     String name;
    EditText nameIput;
    Button submitButton;
    TextView displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameIput = (EditText) findViewById(R.id.name_Input);
        submitButton = (Button) findViewById(R.id.submit_Button);
        displayName = findViewById(R.id.display_name);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                if (!name.isEmpty()) {
                    displayName.setText(name);
                    nameInput.getText().clear();
                } else {
                    displayName.setText("Please enter your name.");
                }
                //displayName.setText(name);
        });

    }

}
}

}
