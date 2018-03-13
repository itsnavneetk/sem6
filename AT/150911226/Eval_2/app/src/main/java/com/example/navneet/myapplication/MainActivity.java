package com.example.navneet.myapplication;

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

    EditText rollEdit;
    EditText nameEdit;
    EditText marksEdit;

    Button insertButton;
    Button updateButton;
    Button viewButton;

    ListView listView;
    SQLiteDatabase database;
    ArrayList<ArrayList> allStudents;

    boolean listOpen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollEdit=(EditText)findViewById(R.id.rollEdit);
        nameEdit=(EditText)findViewById(R.id.nameEdit);
        marksEdit=(EditText)findViewById(R.id.marksEdit);

        insertButton=(Button)findViewById(R.id.insertButton);
        updateButton=(Button)findViewById(R.id.updateButton);
        viewButton=(Button)findViewById(R.id.viewButton);

        listView=(ListView)findViewById(R.id.listView);

        allStudents=new ArrayList<>();

        try {
            database=openOrCreateDatabase("vispi",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS student(roll VARCHAR(20) PRIMARY KEY, name VARCHAR(20), marks VARCHAR(10));");
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll=rollEdit.getText().toString();
                if (roll.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter Roll No",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String name=nameEdit.getText().toString();
                    String marks=marksEdit.getText().toString();

                    try {
                        database.execSQL("INSERT INTO student VALUES('"+roll+"', '"+name+"', '"+marks+"')");
                        Toast.makeText(getApplicationContext(),"Inserted!", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){

                        if (e.toString().contains("code 1555"))
                            Toast.makeText(getApplicationContext(),"Record with Roll No "+roll+" already exists",Toast.LENGTH_SHORT).show();
                    }
                }
                //setList();
            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roll=rollEdit.getText().toString();
                if (roll.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter Roll No",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String name=nameEdit.getText().toString();
                    String marks=marksEdit.getText().toString();

                    try {
                        database.execSQL("UPDATE student SET name='"+name+"', marks='"+marks+"' WHERE roll='"+roll+"'");
                        Toast.makeText(getApplicationContext(),"Updated!", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ignore)
                    {}
                }

                //setList();
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
                rollEdit.setText(allStudents.get(position).get(0).toString());
                nameEdit.setText(allStudents.get(position).get(1).toString());
                marksEdit.setText(allStudents.get(position).get(2).toString());
            }
        });
    }

    protected void setList()
    {
        if (listOpen)
        {
            Cursor c = database.rawQuery("SELECT * FROM student ORDER BY roll", new String[]{});
            if (c.moveToFirst()) {

                ArrayList<String> list = new ArrayList<>();
                allStudents.clear();

                do {
                    String roll = c.getString(0);
                    String name = c.getString(1);
                    String marks = c.getString(2);

                    list.add("Roll No: " + roll + "\nName: " + name + "\nMarks: " + marks);

                    ArrayList<String> student = new ArrayList<>();
                    student.add(roll);
                    student.add(name);
                    student.add(marks);
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
