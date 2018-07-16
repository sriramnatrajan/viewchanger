package com.example.aravind.viewchanger;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {

        DatabaseHelper myDB;
        ArrayList<User> userList;
        ListView listView;
        User user;
        private Paginator paginator;
        private int lastPage,currentPage=0;
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
                Toast.makeText(ViewData.this, "There is nothing in the database", Toast.LENGTH_LONG).show();

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
//            listView.setAdapter(new ArrayAdapter<String>(ViewListContents.this,R.layout.viewcontents_layout,p.generatePage(currentPage)));
                    updateButtons();
                    //Intent in=new Intent(ViewListContents.this,GridActivity.class);
                    //startActivity(in);



                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPage += 1;
                    //listView.setAdapter(new ArrayAdapter<String>(ViewListContents.this,R.layout.viewcontents_layout,p.generatePage(currentPage)));
                    updateButtons();
                    //              Intent in=new Intent(ViewListContents.this,GridActivity.class);
//                startActivity(in);

                    //Previous Button:
                    //Toast.makeText(AddUser.this,"You can add User details only No next Page",Toast.LENGTH_SHORT).show();
                }
            });

        }




        private void updateData()
        {
             //listView.setAdapter(new ArrayAdapter<>(ViewData.this,FourColumn_ListAdapter.class,paginator.getUserList(currentPage)));
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
            else if (currentPage >= 1 && currentPage <=5)
            {
                btnNext.setEnabled(true);
                btnPrevious.setEnabled(true);
            }
            else
            {

                Intent in=new Intent(ViewData.this,UserActivity.class);
                startActivity(in);
            }
        }
    }

    // peopleDB = new DatabaseHelper(this);
        //etName = (EditText) findViewById(R.id.username);
        //etPass = (EditText) findViewById(R.id.password);
        //etPlace = (EditText) findViewById(R.id.place);
        //btnViewData = (Button) findViewById(R.id.veew);
       // btnUpdateData=(Button)findViewById(R.id.Update_btn);
        //etID = (EditText) findViewById(R.id.numid);
        //btnPrevious=(Button)findViewById(R.id.prevBtn);
        //btnNext=(Button)findViewById(R.id.nextBtn);

        //ViewData();
    //    updateData();
        //etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          //  @Override
           // public void onFocusChange(View v, boolean hasFocus) {
             //   if(etName.getText().length()<6)
               // {
                 //   etName.setError("Enter the Username for Update");
                //}
            //}
        //});

        //etPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        //    @Override
        //  public void onFocusChange(View v, boolean hasFocus) {
        //      if(etPass.getText().length()<6)
        //      {
        //          etPass.setError("Enter the Password 6 char/num/sym for Modify");
        //      }
          //  }
        //});

        //etPlace.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          //  @Override
            //public void onFocusChange(View v, boolean hasFocus) {
              //  if(etPlace.getText().length()<3)
                //{
                  //  etPlace.setError("Enter the Which place need to be Update");
                //}
            //}
        //});
       // btnPrevious.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   Intent i=new Intent(ViewData.this,GridActivity.class);
               // startActivity(i);
            //}
        //});

        //btnNext.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Intent y=new Intent(ViewData.this,GridActivity.class);
                //startActivity(y);
                //Toast.makeText(ViewData.this,"You can View User details only No next Page",Toast.LENGTH_SHORT).show();
            //}
        //});




    //}
