package com.anilcanipsalali.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    android.widget.Button recalculateBMIButton;
    TextView bmiDisplay, bmiCategory, genderDisplay, ageDisplay;
    Intent intent;
    ImageView imageView;
    String bmi, height, weight;
    float bmiResult, heightVar, weightVar;
    RelativeLayout layoutBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        calculateBMI();
        clickListener();
    }

    private void init() {
        intent = getIntent();
        recalculateBMIButton = findViewById(R.id.recalculateBMI);
        bmiDisplay = findViewById(R.id.bmiDisplay);
        bmiCategory = findViewById(R.id.bmiCategory);
        genderDisplay = findViewById(R.id.genderDisplay);
        ageDisplay = findViewById(R.id.ageDisplay);
        layoutBG = findViewById(R.id.contentLayout);
        imageView = findViewById(R.id.imageView);
        recalculateBMIButton = findViewById(R.id.recalculateBMI);
        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");
    }

    private void clickListener() {
        recalculateBMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void calculateBMI() {
        heightVar = Float.parseFloat(height);
        weightVar = Float.parseFloat(weight);
        heightVar = heightVar/100;
        bmiResult = weightVar/(heightVar*heightVar);
        bmi = Float.toString(bmiResult);

        if(bmiResult < 18.4) {
            bmiCategory.setText(R.string.thinness);
            layoutBG.setBackgroundColor(Color.parseColor("#cc3300"));
            imageView.setImageResource(R.drawable.ic_risk);
        }
        else if(bmiResult > 18.4 && bmiResult < 24.9) {
            bmiCategory.setText(R.string.normal);
            layoutBG.setBackgroundColor(Color.parseColor("#99cc33"));
            imageView.setImageResource(R.drawable.ic_ok);
        }
        else if(bmiResult > 24.9 && bmiResult < 29.9) {
            bmiCategory.setText(R.string.overweight);
            layoutBG.setBackgroundColor(Color.parseColor("#ffcc00"));
            imageView.setImageResource(R.drawable.ic_problem);
        }
        else if(bmiResult > 29.9 && bmiResult < 34.9) {
            bmiCategory.setText(R.string.fat);
            layoutBG.setBackgroundColor(Color.parseColor("#ffcc00"));
            imageView.setImageResource(R.drawable.ic_problem);
        }
        else if(bmiResult > 34.9 && bmiResult < 44.9) {
            bmiCategory.setText(R.string.fat2);
            layoutBG.setBackgroundColor(Color.parseColor("#cc3300"));
            imageView.setImageResource(R.drawable.ic_risk);
        }
        else if(bmiResult > 44.9) {
            bmiCategory.setText(R.string.fat3);
            layoutBG.setBackgroundColor(Color.parseColor("#cc3300"));
            imageView.setImageResource(R.drawable.ic_risk);
        }

        genderDisplay.setText(intent.getStringExtra("gender"));
        ageDisplay.setText(intent.getStringExtra("age"));
        bmiDisplay.setText(bmi);
    }
}