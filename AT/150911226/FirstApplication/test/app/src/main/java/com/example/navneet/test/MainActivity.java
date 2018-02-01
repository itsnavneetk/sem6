package com.example.navneet.test;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = (RadioGroup)findViewById(R.id.rg);
        r1 = (RadioButton) findViewById(R.id.r1);
        r2 = (RadioButton) findViewById(R.id.r2);
        r3 = (RadioButton) findViewById(R.id.r3);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bn = new Bundle();
                Toast.makeText(getApplicationContext(), "yay, something's clicked!", Toast.LENGTH_SHORT).show();
                String link="";
                if(r1.isChecked())
                    link = "Color.RED";
                if(r2.isChecked())
                    link = "Color.WHITE";
                if(r3.isChecked())
                    link = "Color.BLUE";
                bn.putString("link", link);
                intent.putExtras(bn);
                startActivity(intent);
            }
        });
    }
}
