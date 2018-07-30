package com.example.sood.pomo;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final int pomodoroTechTimeLimit = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar timerProgBar = (ProgressBar) findViewById(R.id.timer_prog_bar);
        final TextView timeTextView = (TextView) findViewById(R.id.time_text_view);

        final int maxProg;
        /*final int tickEvery;
        if(2*60 < Integer.MAX_VALUE)
        {
            maxProg = 2*60;
            tickEvery = 1;
            timerProgBar.setMax((int) maxProg);
        }
        else
        {
            maxProg = 2;
            timerProgBar.setMax((int) maxProg);
            tickEvery = 60;
        }*/

        maxProg = pomodoroTechTimeLimit*60;
        //Clock Timer
        timerProgBar.setMax((int) maxProg);
        timerProgBar.setProgress((int) maxProg);


        Button startToggle = findViewById(R.id.start_button);
        startToggle.setTextColor(getResources().getColor(R.color.textColor));
        /*startToggle.setText(R.string.start_session);
        startToggle.setTextOn(getString(R.string.pause_session));
        startToggle.setTextOff(getString(R.string.resume_session));*/


        startToggle.setVisibility(View.VISIBLE);


        //int preMin = 2;//Integer.parseInt(timeTextView.getText().subSequence(0, 2).toString());
        int preSec = 0;//Integer.parseInt(timeTextView.getText().subSequence(3, 5).toString());

        final CountDownTimer counter = new CountDownTimer((pomodoroTechTimeLimit*60 + preSec)*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                /*int min = Integer.parseInt(timeTextView.getText().subSequence(0, 2).toString());
                int sec = Integer.parseInt(timeTextView.getText().subSequence(3, 5).toString());

                //
                if(sec == 0)
                {
                    sec = 59;
                    min--;
                }
                else
                    sec--;

                String time = String.format("%02d:%02d", min, sec);
                timeTextView.setText(time);

                long secUntilFinished = millisUntilFinished/1000;

                if(secUntilFinished % tickEvery == 0)
                {
                    int prog = timerProgBar.getProgress();
                    prog--;
                    timerProgBar.setProgress(prog);
                */
                long secUntilFinished = millisUntilFinished/1000;

                long minLeft = secUntilFinished/60;
                long secLeft = secUntilFinished%60;

                String time = String.format("%02d:%02d", minLeft, secLeft);
                timeTextView.setText(time);

                //int prog = timerProgBar.getProgress();
                timerProgBar.setProgress((int) secUntilFinished, true);

            }

            @Override
            public void onFinish() {
                //When it's all over
                //Toast
                Toast.makeText(getApplicationContext(), R.string.snackbar_over, Toast.LENGTH_SHORT).show();
            }
        };


        startToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.cancel();
                timerProgBar.setProgress(maxProg, true);
                counter.start();
            }
        });


    }
}
