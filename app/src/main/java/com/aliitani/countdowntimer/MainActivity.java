package com.aliitani.countdowntimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button timerButton;
    boolean countdownOn = false;
    CountDownTimer countdownTimer;


    public void resetTimer() {

        timerTextView.setText("00:30");
        timerSeekBar.setProgress(30);
        countdownTimer.cancel();
        timerButton.setText("GO!");
        timerSeekBar.setEnabled(true);
        countdownOn = false;
    }
    public void updateTimer(int secondsLeft) {

            int minutes = (int) secondsLeft / 60;
            int seconds = secondsLeft - minutes * 60;
            String secondString = Integer.toString(seconds);


            if (seconds <= 9) {
                if (minutes == 10) {

                    timerTextView.setText(Integer.toString(minutes) + ":" + 0 + "" + Integer.toString(seconds));

                } else {

                    timerTextView.setText(0 + "" + Integer.toString(minutes) + ":" + 0 + "" + Integer.toString(seconds));

                }
            } else {
                timerTextView.setText(0 + "" + Integer.toString(minutes) + ":" + Integer.toString(seconds));


            }
    }
    public void timerButtonOn (View view) {

        System.out.println("tapped button");

        if (countdownOn == false) {

            countdownOn = true;
            timerSeekBar.setEnabled(false);
            timerButton.setText("STOP");


            countdownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {


                    resetTimer();
                    MediaPlayer mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaplayer.start();

                    System.out.println("timer is done!");

                }
            }.start();
        } else {

                resetTimer();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.TimerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        timerButton = (Button) findViewById(R.id.timerButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
