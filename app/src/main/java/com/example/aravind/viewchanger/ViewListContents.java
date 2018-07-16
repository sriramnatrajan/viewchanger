package com.example.aravind.viewchanger;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {
    private static final String TAG = ViewListContents.class.getName();

    DatabaseHelper myDB;
    ArrayList<User> userList=new ArrayList<User>();
    ListView listView;
    User user;
   // Paging paging = new Paging();
    //private int totalPages = Paging.ToTAL_NUM_ITEMS / Paging.ITEMS_PER_PAGE;
    Paginator paginator;
    int lastPage,currentPage = 0;
    TextView userid, username, password, place;
   Button btnPrevious,btnNext;
    FourColumn_ListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
        userid = (TextView) findViewById(R.id.userid);
          btnNext = (Button) findViewById(R.id.nextBtn);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.passwordd);
        place = (TextView) findViewById(R.id.placee);
        btnPrevious = (Button) findViewById(R.id.prevBtn);
     // final Button  btnNext = (Button) findViewById(R.id.nextBtn);
        btnPrevious.setVisibility(View.INVISIBLE);
        myDB = new DatabaseHelper(this);
      //  userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(ViewListContents.this, "There is nothing in the database", Toast.LENGTH_LONG).show();

        } else {

            while (data.moveToNext()) {
                user = new User(data.getString(0), data.getString(1), data.getString(2), data.getString(3));               // User(data.getString(1), data.getString(2), data.getString(3), data.getString(4));
                userList.add(user);
            }
             adapter = new FourColumn_ListAdapter(this, R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listitem);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,   final int position, long id) {

                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    adapter.currentPage++;
                    adapter.notifyDataSetChanged();

                 if ((adapter.currentPage*Constant.SHOW_OFFSET)+Constant.SHOW_OFFSET>=userList.size()){

                     btnNext.setVisibility(View.INVISIBLE);
                 }
                    btnPrevious.setVisibility(View.VISIBLE);
                }
            });

        }
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.currentPage--;
                adapter.notifyDataSetChanged();
                if(adapter.currentPage ==0){
                    btnPrevious.setVisibility(View.INVISIBLE);

                }else {
                    btnPrevious.setVisibility(View.VISIBLE);
                }
                btnNext.setVisibility(View.VISIBLE);
                }
        });
        }

}

