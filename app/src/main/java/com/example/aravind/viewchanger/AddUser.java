package com.example.aravind.viewchanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUser extends AppCompatActivity {

    DatabaseHelper peopleDB;
    Button btnAddData,btnCancelData,btnPrevious,btnNext;
    EditText etName, etPass, etPlace, etID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        peopleDB = new DatabaseHelper(this);
        etName = (EditText) findViewById(R.id.username);
        etPass = (EditText) findViewById(R.id.password);
        etPlace = (EditText) findViewById(R.id.place);
        btnAddData = (Button) findViewById(R.id.add_btn);
        btnCancelData=(Button)findViewById(R.id.cancel_btn);
        //btnPrevious=(Button)findViewById(R.id.prevBtn);
        //btnNext=(Button)findViewById(R.id.nextBtn);
        addData();



        //etName.addTextChangedListener(loginTextWatcher);
        //etPass.addTextChangedListener(loginTextWatcher);
      //  etPlace.addTextChangedListener(loginTextWatcher);



        //etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
         // @Override
         //public void onFocusChange(View v, boolean hasFocus) {

              //EditText state = (EditText) findViewById(R.id.txtState);


          //    Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
            //  Matcher ms = ps.matcher(etName.getText().toString());
             // boolean bs = ms.matches();
             // if (bs == false) {

                //  Toast.makeText(getApplicationContext(), "Enter only Alphabets", Toast.LENGTH_LONG).show();


                  //if (ErrorMessage.contains("invalid"))
                    //  ErrorMessage = ErrorMessage + "state,";
                  //else
                    //  ErrorMessage = ErrorMessage + "invalid state,";

              //}

             //   if(etName.getText().length()<4)
               // {
                 //   etName.setError("Username must have minimum 4 length");
                //}
        //  }
        //});
        etName.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if(source.equals(""))
                        {
                            return source;
                        }
                        if(source.toString().matches("[a-zA-Z ]+"))
                        {
                            return source;
                        }
                        return "";
                    }
                }
        });

        etPlace.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if(source.equals(""))
                        {
                            return source;
                        }
                        if(source.toString().matches("[a-zA-Z ]+"))
                        {
                            return source;
                        }
                        return "";
                    }
                }
        });



        etPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ((etPass.getText().toString().length() < 4)) {
                    // Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
                    etPass.setError(" Password must be Valid");


                }


            }
        });

        etPlace.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ((etPlace.getText().toString().length()<2)) {
                    // Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
                    etPlace.setError("Place must be Valid ");


                }


            }
        });
        btnCancelData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddUser.this,GridActivity.class);
                startActivity(i);
                //Toast.makeText(AddUser.this, "Thank You...", Toast.LENGTH_LONG).show();
            }
        });


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

            btnAddData.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty() && !placeInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String pass = etPass.getText().toString();
                String place = etPlace.getText().toString();

                if ((etName.getText().toString().length()==0) && (etName.getText().toString().length()<3)) {
                    etName.setError("Username must have minimum 4 length");
                       }

                else if ((name.length() > 3) && (pass.length() == 0) && (place.length() == 0)) {

                        Toast.makeText(getApplicationContext(), "Enter the Password and Place", Toast.LENGTH_LONG).show();

                    } else if ((name.length() > 3) && (pass.length() == 0) && (place.length() == 0)) {
                        Toast.makeText(getApplicationContext(), "Enter the Username,Password and Place", Toast.LENGTH_LONG).show();

                    } else if ((name.length() < 3) && (pass.length() < 3) && (place.length() < 2)) {
                        Toast.makeText(getApplicationContext(), "Username,Password and Place must be Valid", Toast.LENGTH_LONG).show();
                    } else if (((etName.getText().toString().length() > 3) && (etPass.getText().toString().length() > 3) && (etPlace.getText().toString().length() > 1)))

                    {
                        boolean insertData = peopleDB.addData(name, pass, place);

                        if (insertData == true) {
                            Toast.makeText(AddUser.this, "Data Inserted Successfully...", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AddUser.this, GridActivity.class);
                            startActivity(i);

                        }
                    }
                else {
                        //Toast.makeText(AddUser.this, "Problem Occured dada didn't insert ", Toast.LENGTH_LONG).show();
                        Toast.makeText(AddUser.this, "Each Filed must filled the Valid Data  ", Toast.LENGTH_LONG).show();


                    }

                }

        });




}
}