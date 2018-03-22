package com.example.student.clinic;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private Spinner drop;
    private TextView res;
    SQLiteDatabase database;
    int values[] = {1, 2, 3, 8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drop=(Spinner)findViewById(R.id.spinner2);
        res=(TextView)findViewById(R.id.res);

        //int values[] = {1, 2, 3, 8};
        try {
            database=openOrCreateDatabase("CinicDb.db",MODE_PRIVATE,null);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

       /* drop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String doc = drop.getSelectedItem().toString();

            }
        });
*/
        //on drop
        Cursor c = database.rawQuery("SELECT doctor_id, count(*) FROM patient where doctor_id='"+"8"+"' group by doctor_id", new String[]{});
        String str="";
       if (c.moveToFirst()) {
            do {
                String count = c.getString(1);
                String doc = c.getString(0);
                str = str + "\nDoctor_id: " + doc + " | Number of Patients :"+count+"\n";
            }
            while (c.moveToNext());
           res.setText(str);
            c.close();

        }
    }

 }

