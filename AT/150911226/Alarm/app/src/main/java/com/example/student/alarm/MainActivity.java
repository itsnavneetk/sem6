package com.example.student.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.sql.Time;
public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker=(TimePicker)findViewById(R.id.timePicker);



        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int hour=timePicker.getCurrentHour();
                int minute=timePicker.getCurrentMinute();

                int curHour=new Time(System.currentTimeMillis()).getHours();
                int curMinute=new Time(System.currentTimeMillis()).getMinutes();

                long delay;
                delay=(hour-curHour)*60 + (minute-curMinute);
                delay*=60000;

                if (hour<curHour || (hour==curHour && minute<curMinute))
                    delay+=86400000;

                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent=new Intent(getApplicationContext(), activity_alarm.class);
                        PendingIntent pIntent=PendingIntent.getActivity(getApplicationContext(), (int)System.currentTimeMillis(),intent,0);

                        Notification n=new Notification.Builder(getApplicationContext())
                                .setContentTitle("Alarm!")
                                .setContentText("This is the alarm notification!")
                                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                                .setContentIntent(pIntent)
                                .setAutoCancel(true)
                                .addAction(android.R.drawable.ic_btn_speak_now,"Dismiss",pIntent)
                                .build();

                        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(0,n);

                    }
                },delay);


                delay/=60000;
                int minutesLeft=(int)(delay%60);
                delay/=60;
                int hoursLeft=(int)delay;

                Toast.makeText(getApplicationContext(), "Time Left: "+Integer.toString(hoursLeft)
                        +" hours "+Integer.toString(minutesLeft)+" minutes",Toast.LENGTH_SHORT).show();

            }
        });

    }
}



