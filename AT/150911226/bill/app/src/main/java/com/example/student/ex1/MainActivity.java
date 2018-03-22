package com.example.student.ex1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editId;
    TextView prodNameText;
    TextView prodPriceText;

    Button goButton;
    Button cheapButton;
    Button costlyButton;

    TextView billPrice;
    Button clearButton;
    ListView listView;

    DBHelper database;

    ArrayList<String> billList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId=(EditText)findViewById(R.id.editId);
        prodNameText=(TextView)findViewById(R.id.prodNameText);
        prodPriceText=(TextView)findViewById(R.id.prodPriceText);

        goButton=(Button)findViewById(R.id.goButton);
        cheapButton=(Button)findViewById(R.id.cheapButton);
        costlyButton=(Button)findViewById(R.id.costlyButton);

        billPrice=(TextView)findViewById(R.id.billPrice);
        clearButton=(Button)findViewById(R.id.clearButton);
        listView=(ListView)findViewById(R.id.listView);

        database=new DBHelper(MainActivity.this);

        try{
            database.insertProd("2","SanDisk CruzerBlade", 399);
            database.insertProd("3","HP Computer Mouse", 359);
            database.insertProd("4","Casio Classic Digital Watch", 789);
            database.insertProd("5","Tupperware Bottle", 119);
            database.insertProd("6","Nike Shoes", 2399);
            database.insertProd("7","Rorito Flymax Gel", 10);
        }
        catch (Exception ignore)
        {}


        goButton.setEnabled(false);

        database.execSQL("DELETE FROM bill");

        billList=new ArrayList<>();

        editId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Cursor c=database.getProd(s.toString());

                if (c.moveToFirst())
                {
                    prodNameText.setText("Product Name: "+c.getString(1));
                    prodPriceText.setText("Product Price: ₹ "+c.getString(2)+"/-");
                    goButton.setEnabled(true);
                }
                else
                {
                    prodNameText.setText("Product Name: ");
                    prodPriceText.setText("Product Price: ");
                    goButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pId=editId.getText().toString();

                Cursor c=database.query("SELECT qty FROM bill WHERE prod_id='"+pId+"'");
                if (c.moveToFirst())
                {
                    int qty=Integer.parseInt(c.getString(0))+1;
                    database.updateBill(pId,qty);
                }
                else
                {
                    database.insertBill(editId.getText().toString());
                }

                displayBill();
            }
        });

        cheapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query="SELECT * FROM products WHERE prod_price IN " +
                        "(SELECT MIN(prod_price) FROM products)";

                Cursor c=database.query(query);

                if (c.moveToFirst())
                {
                    editId.setText(c.getString(0));
                    prodNameText.setText("Product Name: "+c.getString(1));
                    prodPriceText.setText("Product Price: ₹ "+c.getString(2)+"/-");
                }
            }
        });

        costlyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query="SELECT * FROM products WHERE prod_price IN " +
                        "(SELECT MAX(prod_price) FROM products)";

                Cursor c=database.query(query);

                if (c.moveToFirst())
                {
                    editId.setText(c.getString(0));
                    prodNameText.setText("Product Name: "+c.getString(1));
                    prodPriceText.setText("Product Price: ₹ "+c.getString(2)+"/-");
                }
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Cursor c=database.query("SELECT * FROM bill");
//                if (c.moveToFirst())
//                {
//                    do {
//                        Toast.makeText(MainActivity.this, c.getString(0), Toast.LENGTH_SHORT).show();
//                    }
//                    while (c.moveToNext());
//                }

                database.execSQL("DELETE FROM bill");
                displayBill();
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String pId=billList.get(position).substring(1,6);

                AlertDialog.Builder removeAlert=new AlertDialog.Builder(MainActivity.this);
                removeAlert.setTitle("Remove Item?");
                removeAlert.setMessage("Do you want to remove the item "+pId+" from the bill?");
                removeAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.execSQL("DELETE FROM bill WHERE prod_id='"+pId+"'");
                        displayBill();
                    }
                });
                removeAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                removeAlert.show();







                return false;
            }
        });


    }

    protected void displayBill()
    {
        billList.clear();

        Cursor c=database.query("SELECT * FROM bill NATURAL JOIN products");
        if (c.moveToFirst())
        {
            int total=0;
            do {
                billList.add("("+c.getString(0)+")   "+c.getString(2)+"\n₹ "+c.getString(3)+"/-   Qty: "+c.getString(1));
                total+=(Integer.parseInt(c.getString(1)) * Integer.parseInt(c.getString(3)));
            }
            while (c.moveToNext());
            billPrice.setText("Total: ₹ "+Integer.toString(total)+"/-");

        }
        else
            billPrice.setText("");

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,billList);

        listView.setAdapter(adapter);
    }
}
