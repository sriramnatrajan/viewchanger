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

public class ViewListData extends AppCompatActivity {
    private static final String TAG = ViewListContents.class.getName();

    DatabaseHelper myDB;
    ArrayList<User> userList;
    ListView listView;
    User user;
    Paginator paginator;
    int lastPage,currentPage=0;
    TextView userid,username,password,place;
    Button btnPrevious,btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);
        userid=(TextView)findViewById(R.id.userid);
        username=(TextView)findViewById(R.id.username);
        password=(TextView)findViewById(R.id.passwordd);
        place=(TextView)findViewById(R.id.placee);
        btnPrevious=(Button)findViewById(R.id.prevBtn);
        btnNext=(Button)findViewById(R.id.nextBtn);
        myDB = new DatabaseHelper(this);

        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(ViewListData.this, "There is nothing in the database", Toast.LENGTH_LONG).show();

        } else {

            while (data.moveToNext()) {
                user = new User(data.getString(0),data.getString(1),data.getString(2),data.getString(3));               // User(data.getString(1), data.getString(2), data.getString(3), data.getString(4));
                userList.add(user);
            }
            FourColumn_ListAdapter adapter = new FourColumn_ListAdapter(this, R.layout.list_adapter_view, userList);
            listView = (ListView) findViewById(R.id.listitem);
            listView.setAdapter(adapter);
            paginator=new Paginator(10,userList);
            lastPage=paginator.getLastPage();
            updateData();
        }
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage -= 1;
                updateData();
                //listView.setAdapter(new ArrayAdapter<String>(ViewListContents.this,FourColumn_ListAdapter.class,paginator.getLastPage(currentPage)));
                //updateButtons();
                //Intent in=new Intent(ViewListContents.this,GridActivity.class);
                //startActivity(in);



            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage += 1;
                updateData();
                //  listView.setAdapter(new ArrayAdapter<String>(ViewListContents.this,R.layout.viewcontents_layout,paginator.getLastPage(lastPage)));
                //updateButtons();
                //Intent in=new Intent(ViewListContents.this,GridActivity.class);
                //startActivity(in);

                //Previous Button:
                //Toast.makeText(AddUser.this,"You can add User details only No next Page",Toast.LENGTH_SHORT).show();
            }
        });

    }




    private void updateData()
    {
        //   ArrayList<User> aPageData=paginator.getUserList(0);
        //   listView.setAdapter(new ArrayAdapter<>(ViewListContents.this,android.R.layout.FourColumn_ListAdapter,paginator.getUserList(currentPage)));
        // listView.setAdapter()(ViewListContents.this,android.R.layout.fourcolumn_adapter,paginator.getUserList(currentPage)));
        //FourColumn_ListAdapter adapter = new FourColumn_ListAdapter(this, R.layout.list_adapter_view, userList);
        //listView.setAdapter(new FourColumn_ListAdapter(ViewListData.this,paginator.getLastPage(currentPage)));
        updateButtons();
    }

    private void updateButtons()
    {
        if(currentPage == 0)
        {
            btnNext.setEnabled(true);
            btnPrevious.setEnabled(false);

        }
        else if(currentPage == lastPage)
        {
            btnPrevious.setEnabled(true);
            btnNext.setEnabled(false);
        }
        else
        if (currentPage >= 1 && currentPage <paginator.getLastPage() )
        {
            btnNext.setEnabled(true);
            btnPrevious.setEnabled(true);
        }
        //else
        //{

        //  Intent in=new Intent(ViewListContents.this,GridActivity.class);
        //startActivity(in);
        //}
    }
}
