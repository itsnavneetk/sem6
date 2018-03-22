package com.example.student.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4;
    EditText t1,t2,t3,t4;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SQLiteDatabase mydatabase = openOrCreateDatabase("Hosp.db",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS patient(Patient_ID INTEGER PRIMARY KEY,Patient_name TEXT NOT NULL,Dept_name TEXT NOT NULL,Doctor_ID INTEGER);");

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        t1 = (EditText)findViewById(R.id.editText);
        t2 = (EditText)findViewById(R.id.editText2);
        t3 = (EditText)findViewById(R.id.editText3);
        t4 = (EditText)findViewById(R.id.editText4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int atn = 0;
                if(!t4.getText().toString().isEmpty())
                    atn = Integer.parseInt(t4.getText().toString());
                if(t1.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Enter Patient ID",Toast.LENGTH_SHORT).show();
                else if(t2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Enter Patient Name",Toast.LENGTH_SHORT).show();
                else if(t3.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Enter Department Name",Toast.LENGTH_SHORT).show();
                else if(t4.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Enter Doctor ID",Toast.LENGTH_SHORT).show();
                else {
                    try {
                        int a=Integer.parseInt(t1.getText().toString());
                        int b=Integer.parseInt(t4.getText().toString());
                        mydatabase.execSQL("INSERT INTO patient VALUES(" + t1.getText().toString() + ",'" + t2.getText().toString() + "','" + t3.getText().toString() + "'," + t4.getText().toString() + ");");
                        Toast.makeText(getApplicationContext(), "Patient details inserted", Toast.LENGTH_SHORT).show();
                    }catch (SQLiteConstraintException sql){
                        Toast.makeText(getApplicationContext(),"Patient ID not unique",Toast.LENGTH_SHORT).show();
                    }
                    catch (NumberFormatException sq2)
                    {
                        Toast.makeText(getApplicationContext(),"Alpha Numeric Not allowed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor results = mydatabase.rawQuery("select * from patient",null);
                List<String> res=new ArrayList<String>();
                Spinner lv_dspAll;
                lv_dspAll=(Spinner)findViewById(R.id.spinner);
                results.moveToFirst();
                if(results!=null) {
                    if(results.moveToFirst()) {
                        do {
                            String ureg=results.getString(results.getColumnIndex("Patient_ID"));
                            String unam=results.getString(results.getColumnIndex("Patient_name"));
                            String usub=results.getString(results.getColumnIndex("Dept_name"));
                            String uattend = results.getString(results.getColumnIndex("Doctor_ID"));
                            String row=ureg +"\t"+unam +"\t"+usub+"\t"+uattend;
                            res.add(row);
                        } while (results.moveToNext());
                    }
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,res);
                lv_dspAll.setAdapter(adapter);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
               /*     if(mydatabase.rawQuery("select * from patient where Patient_ID="+t1.getText().toString(),null)!=null)
                        Toast.makeText(getApplicationContext(),"Patient ID found",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),"Patient ID notfound",Toast.LENGTH_SHORT).show();
                 */   int a=Integer.parseInt(t1.getText().toString());
                    mydatabase.execSQL("UPDATE patient set Dept_name='"+t3.getText().toString()+"' where Patient_ID="+t1.getText().toString());
                    //mydatabase.execSQL("Delete FROM patient where Patient_ID="+t1.getText().toString());
                    //   mydatabase.execSQL("INSERT INTO patient VALUES(" + t1.getText().toString() + ",'" + t2.getText().toString() + "','" + t3.getText().toString() + "'," + t4.getText().toString() + ");");
                    Toast.makeText(getApplicationContext(),"Department Name Updated",Toast.LENGTH_SHORT).show();
                }
                catch (NumberFormatException sq2)
                {
                    Toast.makeText(getApplicationContext(),"Alpha Numeric Not allowed",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Patient ID not found",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(t1.getText().toString());
                mydatabase.execSQL("delete from patient where Patient_ID="+t1.getText().toString());
            }
        });
    }
}