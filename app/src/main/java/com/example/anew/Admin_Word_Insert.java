package com.example.anew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Admin_Word_Insert extends AppCompatActivity {
    EditText word;
    Button insert;
    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_word_insert);
        word = (EditText) findViewById(R.id.Adminword);
        insert = (Button) findViewById(R.id.insertword);
        dbhelper = new Dbhelper(Admin_Word_Insert.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Word = word.getText().toString();

                dbhelper.Datainsert(Word);

                startActivity(new Intent(Admin_Word_Insert.this , MainActivity.class));


            }
        });
    }
}