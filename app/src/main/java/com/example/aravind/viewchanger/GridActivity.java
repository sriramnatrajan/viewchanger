package com.example.aravind.viewchanger;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class GridActivity extends AppCompatActivity {

    DatabaseHelper peopleDB;

    Button signout;

    GridLayout mainGrid;
    ImageView c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        mainGrid = (GridLayout) findViewById(R.id.textGrid);
        signout = (Button) findViewById(R.id.logout_btn);
        //mainGrid = (GridLayout) findViewById(R.id.textGrid);
        peopleDB = new DatabaseHelper(this);


        c1 = (ImageView) findViewById(R.id.first);
        c2 = (ImageView) findViewById(R.id.second);
        c3 = (ImageView) findViewById(R.id.third);
        // ViewData();
        //setSingleEvent(mainGrid);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridActivity.this, AddUser.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(intent);
            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridActivity.this, DeleteData.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(intent);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridActivity.this, ViewListContents.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                startActivity(intent);
               // ViewData();

            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GridActivity.this, "Sign Out Successfully...", Toast.LENGTH_LONG).show();
                Intent u = new Intent(GridActivity.this, MainActivity.class);
                startActivity(u);
            }
        });
    }



   // public void ViewData() {
     //   Cursor data = peopleDB.showData();

       // if (data.getCount() == 0) {
         //   display("Error", "NO Data Found...");
           // return;
        //}
        //StringBuffer buffer = new StringBuffer();
        //while (data.moveToNext()) {
          //  buffer.append("ID:" + data.getString(0) + "\n");
            //buffer.append("Name:" + data.getString(1) + "\n");
            //buffer.append("Password:" + data.getString(2) + "\n");
            //buffer.append("Place:" + data.getString(3) + "\n");

            //display("All Stored Data:", buffer.toString());
       // }
    //}



   // public void display(String title, String message) {
     //   AlertDialog.Builder builder = new AlertDialog.Builder(this);
       // builder.setCancelable(true);
        //builder.setTitle(title);
        //builder.setMessage(message);
        //builder.show();
    //}





}






