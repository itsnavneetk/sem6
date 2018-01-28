package com.example.student.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class home extends AppCompatActivity {

    private TextView mesg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mesg = (TextView)findViewById(R.id.msg);

        Bundle b;
        b = getIntent().getExtras();
        String s = b.getString("name");
        String m = b.getString("mobile");
        mesg.setText("Hello "+s+"!");

    }
}
