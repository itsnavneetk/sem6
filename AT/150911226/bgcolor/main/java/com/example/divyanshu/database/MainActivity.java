package com.example.divyanshu.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText t1, t2, t3;
    Button b1, b2, disp;
    String name, id, city;
    MyDBHelper dbHelper;
    long ins;

    ListView disp_data;
    List<String> res=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        disp = (Button)findViewById(R.id.disp);
        t1 = (EditText)findViewById(R.id.idt);
        t2 = (EditText)findViewById(R.id.namet);
        t3 = (EditText)findViewById(R.id.cityt);
        disp_data = (ListView)findViewById(R.id.empd);
        dbHelper=new MyDBHelper(MainActivity.this);
//        dbHelper.onCreate();
//        final SQLiteDatabase mdb = openOrCreateDatabase("eval", MODE_PRIVATE, null);
//        mdb.execSQL("Create table if not exists tbldetail(Name varchar(25), Branch varchar(25), City varchar(25));");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = String.valueOf(t1.getText());
                name = String.valueOf(t2.getText());
                city = String.valueOf(t3.getText());
                ins = dbHelper.insert(Integer.parseInt(id), name, city);
                if(ins == -1) {
                    Toast.makeText(MainActivity.this, "insert has some error", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"inserted ", Toast.LENGTH_SHORT).show();

            }
        });

//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor rs = mdb.rawQuery("Select * from tbldetail", null);
//                rs.moveToFirst();
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getColumnCount());
////                System.out.println("Hello");
//            }
//        });

        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=dbHelper.getAllEmployee();
                if(c!=null)
                {
                    if(c.moveToFirst()) {
                        do
                        {
                            String eid=c.getString(c.getColumnIndex(dbHelper.ID));
                            String enam=c.getString(c.getColumnIndex(dbHelper.NAME));
                            String eads=c.getString(c.getColumnIndex(dbHelper.ADDRESS));
                            String row=eid +"   "+enam +"  "+eads;
                            res.add(row);
                        } while (c.moveToNext());
                    }
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,res);
                disp_data.setAdapter(adapter);
            }
        });


    }
}
