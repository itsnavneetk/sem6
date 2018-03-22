package com.example.student.picture;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private LinearLayout bg;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bg = (LinearLayout)findViewById(R.id.bg);
        iv = (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent();
        Bundle bn = intent.getExtras();

        String img = bn.getString("img");
        Toast.makeText(getApplicationContext(), "Second Activity:", Toast.LENGTH_SHORT).show();
        if(img.equals("lot"))
            iv.setImageResource(R.drawable.lotus);
        if(img.equals("rose"))
            iv.setImageResource(R.drawable.rose);
        if(img.equals("dal"))
            iv.setImageResource(R.drawable.dalia);

        iv = (ImageView)findViewById(R.id.imageView);
        String clr = bn.getString("clr");
        if(clr.equals("RED"))
            iv.setBackgroundColor(Color.RED);
        if(clr.equals("GREEN"))
            iv.setBackgroundColor(Color.GREEN);
        if(clr.equals("BLUE"))
            iv.setBackgroundColor(Color.BLUE);
    }
}
