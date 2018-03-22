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
import android.widget.Toast;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    EditText patId;
    EditText nameEdit;
    EditText dept;
    EditText docID;

    Button insertButton;
    Button updateButton;
    Button viewButton;
    Button next;
    int values[] = {1, 2, 3, 8};

    ListView listView;

    SQLiteDatabase database;

    ArrayList<ArrayList> allStudents;

    boolean listOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patId=(EditText)findViewById(R.id.rollEdit);
        nameEdit=(EditText)findViewById(R.id.nameEdit);
        dept=(EditText)findViewById(R.id.marksEdit);
        docID=(EditText)findViewById(R.id.docid);

        insertButton=(Button)findViewById(R.id.insertButton);
        updateButton=(Button)findViewById(R.id.updateButton);
        viewButton=(Button)findViewById(R.id.viewButton);
        next=(Button)findViewById(R.id.next);

        listView=(ListView)findViewById(R.id.listView);

        allStudents=new ArrayList<>();

        try {
            database=openOrCreateDatabase("CinicDb.db",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS patient(patient_Id VARCHAR(20) PRIMARY KEY, patient_Name VARCHAR(20), dept_name VARCHAR(10), doctor_id VARCHAR(20));");
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pID=patId.getText().toString();
                if (pID.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter Patient ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String name=nameEdit.getText().toString();
                    String dep=dept.getText().toString();
                    String doc=docID.getText().toString();
                    try {
                        database.execSQL("INSERT INTO patient VALUES('"+pID+"', '"+name+"', '"+dep+"', '"+doc+"')");
                        Toast.makeText(getApplicationContext(),"Inserted!", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){

                        if (e.toString().contains("code 1555"))
                            Toast.makeText(getApplicationContext(),"Record with patient ID "+pID+" already exists",Toast.LENGTH_SHORT).show();
                    }
                }

                //setList();
            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pID=patId.getText().toString();
                if (pID.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter patient ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String name=nameEdit.getText().toString();
                    String dep=dept.getText().toString();
                    String doc=docID.getText().toString();
                    try {
                        database.execSQL("UPDATE patient SET patient_Name='"+name+"', dept_name='"+dep+"', doctor_id='"+doc+"' WHERE patient_Id='"+pID+"'");
                        Toast.makeText(getApplicationContext(),"Updated!", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ignore)
                    {}
                }

               // setList();
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOpen=true;
                setList();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                patId.setText(allStudents.get(position).get(0).toString());
                nameEdit.setText(allStudents.get(position).get(1).toString());
                dept.setText(allStudents.get(position).get(2).toString());
                docID.setText(allStudents.get(position).get(3).toString());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intendB = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intendB);
            }
        });

    }

    protected void setList()
    {
        if (listOpen)
        {
            Cursor c = database.rawQuery("SELECT * FROM patient ORDER BY patient_Id", new String[]{});
            if (c.moveToFirst()) {

                ArrayList<String> list = new ArrayList<>();
                allStudents.clear();

                do {
                    String pid = c.getString(0);
                    String name = c.getString(1);
                    String dep = c.getString(2);
                    String doc = c.getString(3);
                    list.add("Patient_Id: " + pid + "\nPatient_Name: " + name + "\nDept_name: " + dep + "\nDoctor_id: " + doc);

                    ArrayList<String> student = new ArrayList<>();
                    student.add(pid);
                    student.add(name);
                    student.add(dep);
                    student.add(doc);
                    allStudents.add(student);
                }
                while (c.moveToNext());

                c.close();

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, list);

                listView.setAdapter(adapter);
            }
        }
    }
}































