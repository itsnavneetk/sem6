package com.example.navneet.endsem01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private EditText name, text;
    private Button sub, next, nAct;
    SQLiteDatabase db;
    LinearLayout ll;
    private Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.name);
        text = (EditText)findViewById(R.id.text);
        sub = (Button)findViewById(R.id.button);
        next = (Button)findViewById(R.id.button2);
        sp = (Spinner)findViewById(R.id.spinner);
        nAct = (Button)findViewById(R.id.button3);
        ll = (LinearLayout)findViewById(R.id.res);
        try{
            db = openOrCreateDatabase("end", MODE_PRIVATE, null);
            db.execSQL("CREATE table if not exists data(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, data TEXT);");
        }catch (Exception e){
            System.out.println("*******Table creation error*****");
        }
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String query = "INSERT INTO data (name, data) values ('"+name.getText().toString()+"','"+text.getText().toString()+"');";
                    db.execSQL(query);
                    Toast.makeText(MainActivity.this, "Data inserted!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    System.out.println("*******insert error*****"+text.getText().toString());
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor c = db.rawQuery("SELECT * FROM data", new String[]{});
                if (c.moveToFirst()){
                    ArrayList<String> list = new ArrayList<>();
                    String op="";
                    list.clear();
                    do{
                        list.add(""+c.getString(0)+"--"+c.getString(1)+"--"+c.getString(2));
                        op = op + ""+c.getString(0)+"--"+c.getString(1)+"--"+c.getString(2)+"\n";
                    }while (c.moveToNext());
                    c.close();
                    Toast.makeText(MainActivity.this,op, Toast.LENGTH_SHORT).show();
                    //spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, list);
                    sp.setAdapter(adapter);
                }
                ll.setBackgroundColor(Color.GRAY);
            }
        });
        nAct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bn = new Bundle();
                bn.putString("msg", "message from act 1");
                bn.putString("msg1", "message 2");
                bn.putString("msg2", "message 3");
                intent.putExtras(bn);
                startActivity(intent);
            }
        });
    }
}
