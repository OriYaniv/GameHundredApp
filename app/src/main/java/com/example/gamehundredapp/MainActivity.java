package com.example.gamehundredapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewNumber, textViewChances, textViewTimer;
    private Button buttonDouble2, buttonMinus4, buttonPlus5, buttonStartOver;
    private LinearLayout linearLayoutGame;
    private int num, counter = 0;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();
        randomNumbers();
        startCountDownTimer();
    }

    private void initUI() {
        textViewNumber = findViewById(R.id.textViewNumber);
        textViewChances = findViewById(R.id.textViewChances);
        textViewTimer = findViewById(R.id.textViewTimer);
        buttonDouble2 = findViewById(R.id.buttonDouble2);
        buttonMinus4 = findViewById(R.id.buttonMinus4);
        buttonPlus5 = findViewById(R.id.buttonPlus5);
        buttonStartOver = findViewById(R.id.buttonStartOver);
        linearLayoutGame = findViewById(R.id.linearLayoutGame);
    }

    private void initListeners() {
        buttonDouble2.setOnClickListener(this);
        buttonMinus4.setOnClickListener(this);
        buttonPlus5.setOnClickListener(this);
        buttonStartOver.setOnClickListener(this);
    }

    private void randomNumbers() {
        num = new Random().nextInt(10) + 1;

        setTextViewChances();
    }

    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("Seconds remaining: " + millisUntilFinished / 1000);

                if (millisUntilFinished / 1000 % 10 == 0) {
                    counter++;
                }
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("Game Over");

                linearLayoutGame.setVisibility(View.GONE);
                buttonStartOver.setVisibility(View.VISIBLE);

                cancel();
            }
        }.start();
    }

    private void setTextViewChances() {
        textViewNumber.setText("Your number now is " + num);
    }

    private void getData() {
        setTextViewChances();

        counter++;

        if (num == 100) {
            textViewChances.setText("You got it right in " + counter + " chances");

            countDownTimer.cancel();

            counter = 0;

            buttonStartOver.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPlus5) {
            num += 5;

            getData();
        }
        if (v.getId() == R.id.buttonMinus4) {
            num -= 4;

            getData();
        }
        if (v.getId() == R.id.buttonDouble2) {
            num *= 2;

            getData();
        }
        if (v.getId() == R.id.buttonStartOver) {
            linearLayoutGame.setVisibility(View.VISIBLE);

            textViewChances.setText("");

            counter = 0;

            setTextViewChances();
            startCountDownTimer();
        }
    }

}
