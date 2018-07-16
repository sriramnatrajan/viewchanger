package com.example.aravind.viewchanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserData extends AppCompatActivity {

    DatabaseHelper peopleDB;

    Button btnAddData,btnCancelData;
    EditText etName, etPass, etPlace, etID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_data);

        peopleDB = new DatabaseHelper(this);
        etName = (EditText) findViewById(R.id.username);
        etPass = (EditText) findViewById(R.id.password);
        etPlace = (EditText) findViewById(R.id.place);
        btnAddData = (Button) findViewById(R.id.add_btn);
        btnCancelData=(Button)findViewById(R.id.cancel_btn);
        //btnPrevious=(Button)findViewById(R.id.prevBtn);
        //btnNext=(Button)findViewById(R.id.nextBtn);
        addData();

        etName.addTextChangedListener(loginTextWatcher);
        etPass.addTextChangedListener(loginTextWatcher);
        etPlace.addTextChangedListener(loginTextWatcher);



        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(etName.getText().length()<4)
                {
                    etName.setError("Username must have minimum 4 length");
                }
            }
        });

        etPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(etPass.getText().length()<4)
                {
                    etPass.setError("Password must have minimum 4 length");
                }
            }
        });

        etPlace.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(etPlace.getText().length()<2)
                {
                    etPlace.setError("Place must have minimum 2 length");
                }
            }
        });
        btnCancelData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddUserData.this,GridActivity.class);
                startActivity(i);
                //Toast.makeText(AddUserData.this, "Thank You...", Toast.LENGTH_LONG).show();
            }
        });

        if( etName.getText().length()<4){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(AddUserData.this, "To Fill Each Field after to move next...", Toast.LENGTH_LONG).show();

           // etName.setError( "User name is required!" );

        }else{
           Intent i = new Intent(getApplicationContext(), AddUserData.class);
         startActivity(i);
        }

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = etName.getText().toString().trim();
            String passwordInput=etPass.getText().toString().trim();
            String placeInput=etPlace.getText().toString().trim();

            btnAddData.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty() && !placeInput.isEmpty() );
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };




    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //if((etName.getText().toString().equals(""))&& (etPass.getText().toString().equals("")) && (etPlace.getText().toString().equals("")))
                //{
                  //  Toast.makeText(getApplicationContext(),"Please to Fill All the Fields...",Toast.LENGTH_SHORT).show();

                //}

                String name = etName.getText().toString();
                String pass = etPass.getText().toString();
                String place = etPlace.getText().toString();




                boolean insertData = peopleDB.addData(name, pass, place);
                if (insertData == true) {
                    Toast.makeText(AddUserData.this, "Data Inserted Successfully...", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AddUserData.this,UserActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(AddUserData.this, "Problem Occured dada didn't insert ", Toast.LENGTH_LONG).show();

                }

            }
        });




    }
}