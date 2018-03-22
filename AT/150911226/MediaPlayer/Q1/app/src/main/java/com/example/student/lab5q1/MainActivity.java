package com.example.student.lab5q1;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private Button b1,b2,b3,b4;
    private MediaPlayer mediaplayer;
    private double startTime=0,stopTime=0;
    private Handler myHandler= new Handler();
    private int forTime=5000,backTime=5000;
    private SeekBar seekBar;
    public static int oneTimeOnly=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.back);
        b2=(Button)findViewById(R.id.pauseplay);
        b3=(Button)findViewById(R.id.forw);
        b4=(Button)findViewById(R.id.stoppie);
        mediaplayer= MediaPlayer.create(this,R.raw.ready);
        ((TextView)findViewById(R.id.hahaha)).setText("Song");
        seekBar=(SeekBar)findViewById(R.id.seekb);
        seekBar.setClickable(true);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Playing Song", Toast.LENGTH_SHORT).show();

                stopTime=mediaplayer.getDuration();
                startTime=mediaplayer.getCurrentPosition();
                if(oneTimeOnly==0)
                {   mediaplayer.start();
                    seekBar.setMax((int)stopTime);
                    b2.setText("Paus");
                    ((TextView)findViewById(R.id.t1)).setText(String.format("%d min %d sec", TimeUnit.MILLISECONDS.toMinutes((long)startTime),TimeUnit.MILLISECONDS.toSeconds((long)startTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)startTime))));
                    ((TextView)findViewById(R.id.t2)).setText(String.format("%d min %d sec",TimeUnit.MILLISECONDS.toMinutes((long)stopTime),TimeUnit.MILLISECONDS.toSeconds((long)stopTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)stopTime))));
                    seekBar.setProgress((int)startTime);

                    oneTimeOnly=1;
                }
                else if(oneTimeOnly==1)
                {
                    b2.setText("Play");
                    mediaplayer.pause();
                    oneTimeOnly=0;
                }
                myHandler.postDelayed(UpdateSongTime,100);
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.pause();
                mediaplayer.seekTo(0);
                b2.setText("Play");
                oneTimeOnly=0;
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = (int) startTime;
                if((temp-backTime)>0)
                {
                    startTime=startTime-backTime;
                    mediaplayer.seekTo((int)startTime);
                }
                else
                {
                    mediaplayer.seekTo(0);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = (int) startTime;
                if((temp+forTime)<=stopTime)
                {
                    startTime=startTime+forTime;
                    mediaplayer.seekTo((int)startTime);
                }
                else
                {
                    mediaplayer.seekTo((int)stopTime);
                }

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaplayer.seekTo(seekBar.getProgress());

            }
        });

    }
    private Runnable UpdateSongTime= new Runnable() {
        @Override
        public void run() {
            startTime=mediaplayer.getCurrentPosition();
            ((TextView)findViewById(R.id.t1)).setText(String.format("%d min %d sec",TimeUnit.MILLISECONDS.toMinutes((long)startTime),TimeUnit.MILLISECONDS.toSeconds((long)startTime)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)startTime))));
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this,100);
        }
    };
}