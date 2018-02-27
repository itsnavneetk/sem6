package com.example.student.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class scoreCard extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        txt = (TextView)findViewById(R.id.score);

        Bundle bn = getIntent().getExtras();
        String s = bn.getString("scr");
        txt.setText(s);
    }
}
