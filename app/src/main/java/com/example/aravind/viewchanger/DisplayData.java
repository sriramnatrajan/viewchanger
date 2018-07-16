package com.example.aravind.viewchanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DisplayData extends AppCompatActivity {

    Button btnView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        btnView=(Button)findViewById(R.id.btnview);
        databaseHelper=new DatabaseHelper(this);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DisplayData.this,ViewListContents.class);
                startActivity(intent);
            }
        });
    }
}
