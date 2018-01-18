package com.example.student.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    //declaration
    private EditText uname;
    private EditText pass;
    private EditText mob;
    private Button submit;
    private TextView msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        uname = (EditText)findViewById(R.id.uname);
        pass = (EditText)findViewById(R.id.pass);
        mob = (EditText)findViewById(R.id.phone);
        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(uname.getText());
                String password = String.valueOf(pass.getText());
                String mobile = String.valueOf(mob.getText());

                if (username.equals("navneet") && password.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Successfull login!", Toast.LENGTH_SHORT).show();
                    //open new activity
                    Intent intendB = new Intent(MainActivity.this, home.class);
                    Bundle bn = new Bundle();
                    bn.putString("name",username);
                    bn.putString("mobile",mobile);
                    intendB.putExtras(bn);
                    startActivity(intendB);
                }else{
                    Toast.makeText(getApplicationContext(), "Unauthorized Access !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
