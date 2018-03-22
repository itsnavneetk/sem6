package db.attendance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase dbs=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final EditText reg,st_name,sub_name,attd;
        Button ins,disp,atted,shrt;
        final ListView lv_dspAll;
        final List<String> res=new ArrayList<String>();
        reg=(EditText)findViewById(R.id.editText);
        st_name=(EditText)findViewById(R.id.editText3);
        sub_name=(EditText)findViewById(R.id.editText4);
        attd=(EditText)findViewById(R.id.editText5);
        lv_dspAll=(ListView)findViewById(R.id.lv_dsp);

        ins=(Button)findViewById(R.id.button);
        disp=(Button)findViewById(R.id.button2);
        atted=(Button)findViewById(R.id.button3);
        shrt=(Button)findViewById(R.id.button4);

        dbs=openOrCreateDatabase("UserDB", Context.MODE_PRIVATE,null);
        dbs.execSQL("CREATE TABLE IF NOT EXISTS STUDENT(Reg_No INTEGER PRIMARY KEY,Std_Name TEXT NOT NULL,Sub_Name TEXT NOT NULL,Attend_Perc INTEGER NOT NULL);");
        dbs.execSQL("CREATE TABLE IF NOT EXISTS STUDENT_ATTEND(Reg_No INTEGER,Std_Name TEXT NOT NULL,Sub_Name TEXT NOT NULL,Attend_Perc INTEGER NOT NULL)");




        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbs.execSQL("INSERT INTO STUDENT(Reg_No,Std_Name,Sub_Name,Attend_Perc) VALUES('"+reg.getText()+"','"+st_name.getText()+"','"+sub_name.getText()+"','"+attd.getText()+"');");
                Toast.makeText(getApplicationContext(),"Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=dbs.rawQuery("SELECT * FROM STUDENT_ATTEND",null);
                if(c!=null)
                {
                    if(c.moveToFirst())
                    {
                        do
                        {
                            String enum1 = c.getString(c.getColumnIndex("Reg_No"));
                            String ename1 = c.getString(c.getColumnIndex("Std_Name"));
                            String ename2 = c.getString(c.getColumnIndex("Sub_Name"));
                            String dnum1 = c.getString(c.getColumnIndex("Attend_Perc"));
                            String row=enum1 +" "+ename1 +" "+ename2+" "+dnum1;
                            res.add(row);

                        } while (c.moveToNext());
                    }
                }
                else{
                    st_name.setText("adf");
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,res);
                lv_dspAll.setAdapter(adapter);

            }
        });
        atted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=attd.getText().toString();
                if(Integer.parseInt(n)<75)
                {
                dbs.execSQL("INSERT INTO STUDENT_ATTEND(Reg_No,Std_Name,Sub_Name,Attend_Perc) VALUES('"+reg.getText()+"','"+st_name.getText()+"','"+sub_name.getText()+"','"+attd.getText()+"');");
                Toast.makeText(getApplicationContext(),"Inserted in Stud_attend", Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(getApplicationContext(),"Above 75", Toast.LENGTH_SHORT).show();
                }
            }

        });
        shrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
