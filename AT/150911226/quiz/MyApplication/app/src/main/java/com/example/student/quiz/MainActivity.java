package com.example.student.quiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button bAlert;
    final Context context = this;
    private RadioButton a, b, c, d, a2, b2, c2, d2;
    private RadioGroup radio;
    @Override
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
