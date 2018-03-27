package com.example.navneet.endsem01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private TextView tv;
    SQLiteDatabase db;
    private ListView list;
    ArrayList<ArrayList> mainList = new ArrayList<>();
    private EditText uName;
    private EditText uData;
    String selectID;
    Button upd, next1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView)findViewById(R.id.textView2);
        list = (ListView)findViewById(R.id.list);
        uName = (EditText)findViewById(R.id.uName);
        uData = (EditText)findViewById(R.id.uText);
        upd = (Button)findViewById(R.id.updateB);
        next1 = (Button)findViewById(R.id.next1);
        final Context context = this;

        Bundle bn = getIntent().getExtras();
        String msg = bn.getString("msg");
        tv.setText(msg);
        try{
            db = openOrCreateDatabase("end", MODE_PRIVATE, null);
        }catch (Exception e){
            System.out.println("DB open error");
        }

        setList();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
           @Override
            public void onItemClick(AdapterView<?> parent, View view, int postition, long id){
               int itemPos = postition;
               String itemVal = (String)list.getItemAtPosition(itemPos);
               Toast.makeText(getApplicationContext(), "--"+itemVal, Toast.LENGTH_SHORT).show();
                uName.setText(mainList.get(itemPos).get(1).toString());
                uData.setText(mainList.get(itemPos).get(2).toString());
                selectID = mainList.get(itemPos).get(0).toString();
           }
        });

        upd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    if (!selectID.equals("")) {
                        db.execSQL("UPDATE data set name = '" + uName.getText().toString() + "', data='" + uData.getText().toString() + "' where id=" + selectID + ";");
                        Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
                        setList();
                    }
                }catch (Exception e){
                    System.out.println("Update error");
                }
            }
        });
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Alert").setMessage("Submit??").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                        Toast.makeText(getApplicationContext(), "Opening! ",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();//close the dialog box }
                    }
                });
                AlertDialog alertdia = alert.create();
                alertdia.show();
            }
        });
    }
    public void setList(){
        Cursor c = db.rawQuery("SELECT * from data", new String[]{});
        if(c.moveToFirst()){
          ArrayList<String> ltemp = new ArrayList<>();
          mainList.clear();
          do {
              String id = c.getString(0);
              String name = c.getString(1);
              String data = c.getString(2);
              ltemp.add(id+"--"+name+"--"+data);
              ArrayList<String> temp = new ArrayList<>();
              temp.add(id);
              temp.add(name);
              temp.add(data);
              mainList.add(temp);
          }while (c.moveToNext());
          c.close();
          ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, ltemp);
          list.setAdapter(adapter);
        }
    }
}
