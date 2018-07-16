package com.example.aravind.viewchanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteData extends AppCompatActivity {
    DatabaseHelper peopleDB;
    Button btnDeleteData,btnCancel;
    EditText etName, etPass, etPlace, etID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

        peopleDB = new DatabaseHelper(this);
       // etName = (EditText) findViewById(R.id.username);
        //etPass = (EditText) findViewById(R.id.password);
        //etPlace = (EditText) findViewById(R.id.place);
        btnDeleteData = (Button) findViewById(R.id.Delete_btn);
       btnCancel=(Button)findViewById(R.id.cancel_btn);
        //btnNext=(Button)findViewById(R.id.nextBtn);

        etID = (EditText) findViewById(R.id.numid);
        DeleteData();
        btnDeleteData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(etID.getText().length()>1)
                {
                    etName.setError("The Registered User Id must for to delete the Data");
                }
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
               // Toast.makeText(DeleteData.this,"Thank You",Toast.LENGTH_LONG).show();

                   Intent i=new Intent(DeleteData.this,GridActivity.class);
                startActivity(i);
            }
        });

        //btnNext.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Intent y=new Intent(DeleteData.this,ViewData.class);
                //startActivity(y);
                //Toast.makeText(DeleteData.this,"You can delete User details only No Move next Page",Toast.LENGTH_SHORT).show();
            //}
        //});


    }

        public void DeleteData()

        {
            btnDeleteData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int temp = etID.getText().toString().length();
                    if (temp > 0) {
                        Integer deleteRow = peopleDB.deleteData(etID.getText().toString());
                        if (deleteRow > 0) {
                            Toast.makeText(DeleteData.this, "Successfully Deleted the data", Toast.LENGTH_LONG).show();
                            Intent i =new Intent(DeleteData.this,GridActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(DeleteData.this, "Somthing Went wrong..", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(DeleteData.this, "You Must enter an ID to Delete..", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
