package com.anilcanipsalali.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    android.widget.Button calculateBMIButton;
    TextView currentHeightTV, currentAgeTV, currentWeightTV;
    ImageView incAgeIV, incWeightIV, decWeightIV, decAgeIV;
    SeekBar seekbarHeight;
    RelativeLayout maleLayout, femaleLayout;
    int weightVar = 70, ageVar = 21, currentHeight;
    String progressVar = "170", typeOfUser="0", weightTemp="70", ageTemp="21";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        clickListener();
    }

    private void clickListener() {
        calculateBMIButton.setOnClickListener(view -> {
            if(typeOfUser.equals("0")) {
                Toast.makeText(getApplicationContext(), "You forgot to choose your gender!", Toast.LENGTH_SHORT).show();
            }
            else if(progressVar.equals("0")) {
                Toast.makeText(getApplicationContext(), "You forgot to choose your height!", Toast.LENGTH_SHORT).show();
            }
            else if(ageVar < 1 || weightVar < 1) {
                Toast.makeText(getApplicationContext(), "You entered your weight or age incorrectly!", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("gender", typeOfUser);
                intent.putExtra("height", progressVar);
                intent.putExtra("weight", weightTemp);
                intent.putExtra("age", ageTemp);
                startActivity(intent);
            }
        });

        maleLayout.setOnClickListener(view -> {
            maleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.genderfocus_bg));
            femaleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gender_not_focus_bg));
            typeOfUser="Male";
        });

        femaleLayout.setOnClickListener(view -> {
            femaleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.genderfocus_bg));
            maleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gender_not_focus_bg));
            typeOfUser="Female";
        });

        seekbarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentHeight = i;
                progressVar = String.valueOf(currentHeight);
                currentHeightTV.setText(progressVar);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        incAgeIV.setOnClickListener(view -> {
            ageVar = ageVar+1;
            ageTemp=String.valueOf(ageVar);
            currentAgeTV.setText(ageTemp);
        });

        decAgeIV.setOnClickListener(view -> {
            ageVar = ageVar-1;
            ageTemp=String.valueOf(ageVar);
            currentAgeTV.setText(ageTemp);
        });

        incWeightIV.setOnClickListener(view -> {
            weightVar = weightVar+1;
            weightTemp = String.valueOf(weightVar);
            currentWeightTV.setText(weightTemp);
        });

        decWeightIV.setOnClickListener(view -> {
            weightVar = weightVar-1;
            weightTemp = String.valueOf(weightVar);
            currentWeightTV.setText(weightTemp);
        });

    }

    public void init() {
        calculateBMIButton = findViewById(R.id.calculateBMI);
        currentAgeTV = findViewById(R.id.currentAge);
        currentWeightTV = findViewById(R.id.currentWeight);
        currentHeightTV = findViewById(R.id.currentHeight);
        incAgeIV = findViewById(R.id.plusAge);
        incWeightIV = findViewById(R.id.plusWeight);
        decAgeIV = findViewById(R.id.minusAge);
        decWeightIV = findViewById(R.id.minusWeight);
        seekbarHeight = findViewById(R.id.seekbarHeight);
        maleLayout = findViewById(R.id.maleLayout);
        femaleLayout = findViewById(R.id.femaleLayout);
        seekbarHeight.setMax(251);
        seekbarHeight.setProgress(170);
    }
}