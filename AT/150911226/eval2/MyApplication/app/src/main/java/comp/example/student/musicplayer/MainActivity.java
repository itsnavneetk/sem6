package comp.example.student.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}






































/*
package comp.example.student.playmusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.ListView;

import java.security.Key;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    ListView list;

    private ArrayList<String> arr = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.glist);
       // selected = (TextView)findViewById(R.id.textView2);
        String[] songs = new String[]{"docomo_song", "song", "tone"};
        ArrayAdapter<String> arrsongs=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, songs);
        list.setAdapter(arrsongs);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemposition=position;
                String itemVal=(String)list.getItemAtPosition(itemposition);

                Intent intendB = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bn = new Bundle();
                bn.putString("enc",itemVal);
                intendB.putExtras(bn);
                startActivity(intendB);



                 } });
    }
}

 */


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


/*
alert
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bAlert = (Button) findViewById(R.id.button);
        radio = (RadioGroup)findViewById(R.id.rg);
        b = (RadioButton)findViewById(R.id.ans1);
        b2 = (RadioButton)findViewById(R.id.ans2);

        bAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int scr=0;
                final Intent intent = new Intent(MainActivity.this, scoreCard.class);
                Bundle bn = new Bundle();
                if (b.isChecked())
                    scr++;
                if (b2.isChecked())
                    scr++;

                bn.putString("scr", scr+"");
                intent.putExtras(bn);

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Alert").setMessage("Submit??").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Calculating scores! ",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
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

    }
}

 */