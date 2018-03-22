package comp.example.student.playsong;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.os.Handler;
import android.widget.*;
import java.util.concurrent.TimeUnit;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    private Button b1, b2, b3;
    private MediaPlayer mediaPlayer;
    private TextView tc, tt;
    final Context context = this;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    int song=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b3 = (Button) findViewById(R.id.button3);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        tc = (TextView) findViewById(R.id.ctime);
        tt = (TextView) findViewById(R.id.ttime);
        mediaPlayer = MediaPlayer.create(this, R.raw.docomo_song);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Change the song").setMessage("Do you wish to change the song?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.pause();
                        myfun();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();//close the dialog box }
                    }
                });
                AlertDialog alertdia = alert.create();
                alertdia.show();
            }
        });

        //media controls
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                tt.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                tc.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );
                myHandler.postDelayed(UpdateSongTime,100);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });



    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tc.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            myHandler.postDelayed(this, 100);
        }
    };


    public void myfun(){
            mediaPlayer.stop();
            Toast.makeText(getApplicationContext(), "Changing song", Toast.LENGTH_SHORT).show();
            if(song==0) {
                mediaPlayer = MediaPlayer.create(this, R.raw.tone);
                song=1;
            }
            else {
                mediaPlayer = MediaPlayer.create(this, R.raw.docomo_song);
                song=0;
            }
    }
}


































/*
package comp.example.student.playmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Key;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Main2Activity extends AppCompatActivity {
    private Button b1, b2;
    private MediaPlayer mediaPlayer;
    int status = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        final Bundle b;
        b = getIntent().getExtras();
        final String m = b.getString("enc");



        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        if(m.contains("docomo"))
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.docomo_song);
        }
        else if(m.contains("song"))
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.song);
        }
        else{
            mediaPlayer = MediaPlayer.create(this, R.raw.tone);
        }




    }

    public void toggle(View view)
    {
        if(status==1)
        {
            mediaPlayer.pause();
            status = 0;
        }
        else{
            mediaPlayer.start();
            status=1;
        }
    }

    public void stop(View view)
    {
     //   moveTaskToBack(true);
       // android.os.Process.killProcess(android.os.Process.myPid());

        Intent intendB = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intendB);
        mediaPlayer.pause();


        //  System.exit(1);
    }
}

 */


