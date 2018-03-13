package com.example.navneet.dbhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    EditText num,name,ads;
    Button ins,disp,updt,delt;
    ListView disp_data;
    List<String> res=new ArrayList<String>();
    MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num=(EditText)findViewById(R.id.et_empno);
        name=(EditText)findViewById(R.id.et_ename);
        ads=(EditText)findViewById(R.id.et_adds);
        ins=(Button)findViewById(R.id.bt_ins);
        updt=(Button)findViewById(R.id.bt_update);
        delt=(Button)findViewById(R.id.bt_del);
        disp=(Button)findViewById(R.id.bt_disp);
        disp_data=(ListView)findViewById(R.id.lv_getEmp);
        dbHelper=new MyDBHelper(MainActivity.this);
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long insres=dbHelper.insert(Integer.parseInt(num.getText().toString()),name.getText().toString().trim(),ads.getText().toString().trim());
                if(insres==-1)
                    Toast.makeText(MainActivity.this,"insert has some error",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"inserted ",Toast.LENGTH_SHORT).show();

            }
        });
        updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long upres=dbHelper.update(Integer.parseInt(num.getText().toString()),name.getText().toString().trim(),ads.getText().toString().trim());
                if(upres==0)
                    Toast.makeText(MainActivity.this,"Update has some error",Toast.LENGTH_SHORT).show();
                else if(upres==1)
                    Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();
            }
        });
        delt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long delres=dbHelper.delete(Integer.parseInt(num.getText().toString()));
                if(delres==0)
                    Toast.makeText(MainActivity.this,"Delete has some error",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Deleted ",Toast.LENGTH_SHORT).show();

            }
        });
        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
