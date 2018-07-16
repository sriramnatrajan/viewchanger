package com.example.aravind.viewchanger;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2;

    DatabaseHelper db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        e1 = (EditText) findViewById(R.id.username);
        e2 = (EditText) findViewById(R.id.password);
        b1 = (Button) findViewById(R.id.login_btn);
        b2 = (Button) findViewById(R.id.cancel_btn);

         e1.addTextChangedListener(loginTextWatcher);
         e2.addTextChangedListener(loginTextWatcher);

         //Toast.makeText(MainActivity.this,"Please enter the Username and Password after only login button be enabled...",Toast.LENGTH_LONG).show();

        e1.setFilters(new InputFilter[]{
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


        e2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if((e2.getText().length()==0))
                {
                    // Validation for Invalid Email Address
                    // Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                    e2.setError("Password cannot be blank");

                    return;
                }

            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Intent i = new Intent(MainActivity.this,null);
               //startActivity(i);
                //                     Toast.makeText(getApplicationContext(),"Thank You...",Toast.LENGTH_SHORT).show();

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(MainActivity.this,GridActivity.class);
                //startActivity(i);

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();

                Boolean chknamepass = db.chkpassword(s1, s2);
                        if((e1.getText().toString().length()==0) && (e1.getText().toString().length()<3)) {
                            // Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
                            e1.setError("Name cannot be blank");
                            return;

                        }
                        if((e1.getText().toString().length()==0)  && (e2.getText().toString().length()==0))
                {
                    Toast.makeText(getApplicationContext(), "Enter the Username and Password",     Toast.LENGTH_LONG).show();

                }
               else if((e1.getText().toString().length()<3)  && (e2.getText().toString().length()<3)) {
                    Toast.makeText(getApplicationContext(), "Enter the Valid Username and Password", Toast.LENGTH_LONG).show();
                }

                else if((e1.getText().toString().length()>3) && (e2.getText().toString().length()==0)) {
                    // Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Enter the Valid  Password", Toast.LENGTH_LONG).show();
                            return;

                }

//                    else
  //                                 {
    //                 Toast.makeText(getApplicationContext(), "Wrong Username and Password",     Toast.LENGTH_LONG).show();
      //              }




                 else if(e1.getText().toString().equals("ADMIN") &&
                        e2.getText().toString().equals("ADMIN")) {
                    Toast.makeText(getApplicationContext(),"Admin Login Successfully...",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this,GridActivity.class);
                    startActivity(i);
                    //correcct password
                }
                        else if(e1.getText().toString().equals("ADMIN") &&
                                e2.getText().toString().length()>1) {
                            Toast.makeText(getApplicationContext(),"Password Invalid...",Toast.LENGTH_LONG).show();
                           // Intent i = new Intent(MainActivity.this,GridActivity.class);
                            //startActivity(i);
                            //correcct password
                        }
                        else if((e1.getText().toString().length()>1) &&
                e2.getText().toString().equals("ADMIN")) {
                            Toast.makeText(getApplicationContext(),"Username Invalid...",Toast.LENGTH_LONG).show();
                           // Intent i = new Intent(MainActivity.this,GridActivity.class);
                            //startActivity(i);
                            //correcct password
                        }





                        //  String s1 = e1.getText().toString();
                //String s2 = e2.getText().toString();


                //Boolean chknamepass = db.chkpassword(s1, s2);
               else if (chknamepass == true) {
                    Toast.makeText(getApplicationContext(), "Login Successfully...", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(i);
                }
                else if  (chknamepass != true) {
                    Toast.makeText(getApplicationContext(), "Please be registered...", Toast.LENGTH_LONG).show();
                   // Intent i = new Intent(MainActivity.this, UserActivity.class);
                    //startActivity(i);
                }
               else
                              {
                Toast.makeText(getApplicationContext(), "Wrong Username and Password",     Toast.LENGTH_LONG).show();
               }



            }


        });


    }
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
             String usernameInput = e1.getText().toString().trim();
             String passwordInput=e2.getText().toString().trim();

             b1.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}


