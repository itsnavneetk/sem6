package com.example.student.picture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton r, g, b;
    private ImageButton rose, dal, lot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup)findViewById(R.id.rg);
        r = (RadioButton)findViewById(R.id.red);
        g = (RadioButton)findViewById(R.id.green);
        b = (RadioButton)findViewById(R.id.blue);

        rose = (ImageButton)findViewById(R.id.imageButton);
        dal = (ImageButton)findViewById(R.id.imageButton2);
        lot = (ImageButton)findViewById(R.id.imageButton3);

        final Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        final Bundle bn = new Bundle();


        rose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bg = "";
                if(r.isChecked()){
                    bg = "RED";
                } if(b.isChecked()){
                    bg = "BLUE";
                } if(g.isChecked()){
                    bg = "GREEN";
                }
                String flower = "rose";
                bn.putString("clr", bg);
                bn.putString("img", flower);

                intent.putExtras(bn);
                startActivity(intent);
            }
        });

        dal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bg = "";
                if(r.isChecked()){
                    bg = "RED";
                } if(b.isChecked()){
                    bg = "BLUE";
                } if(g.isChecked()){
                    bg = "GREEN";
                }
                String flower = "dal";
                bn.putString("clr", bg);
                bn.putString("img", flower);

                intent.putExtras(bn);
                startActivity(intent);
            }
        });

        lot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bg = "";
                if(r.isChecked()){
                    bg = "RED";
                } if(b.isChecked()){
                    bg = "BLUE";
                } if(g.isChecked()){
                    bg = "GREEN";
                }
                String flower = "lot";
                bn.putString("clr", bg);
                bn.putString("img", flower);

                intent.putExtras(bn);
                startActivity(intent);
            }
        });

    }
}













