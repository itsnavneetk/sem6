package com.example.student.grocerylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    private TextView selected;
    private ArrayList<String> arr = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.glist);
        selected = (TextView)findViewById(R.id.textView3);
        String[] fruits = new String[]{"Apple", "Pineapple", "Mango", "Peach", "Orange"};
        ArrayAdapter<String> arrFruits=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, fruits);
        list.setAdapter(arrFruits);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemposition=position;
                String itemVal=(String)list.getItemAtPosition(itemposition);

                arr.add(itemVal);
                for(int i=0; i<arr.size(); i++)
                    selected.setText("Selected Items : \n * "+ arr.get(i));

                Toast.makeText(getApplicationContext(), "item selected : " +itemVal ,Toast.LENGTH_SHORT).show(); } });
    }

}
