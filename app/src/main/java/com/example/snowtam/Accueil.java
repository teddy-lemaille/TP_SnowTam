package com.example.snowtam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        bottomNavigationView= (BottomNavigationView) findViewById(R.id.nav_bot);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().equals("Aide")){
                    Toast.makeText(Accueil.this,"aide",Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });


        Button Add = (Button) findViewById(R.id.buttonAdd);
        ListView list = (ListView) findViewById(R.id.listCodeIOCA);
        EditText CodeIOCA = (EditText) findViewById(R.id.editTextIOCA);
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        list.setAdapter(adapter);

        Add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                arrayList.add(CodeIOCA.getText().toString());
                adapter.notifyDataSetChanged();
                CodeIOCA.setText("");
            }
        });
        Button validate = (Button) findViewById(R.id.buttonValidate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this,Affsnowtam.class);
                i.putExtra("List",arrayList);
                i.putExtra("id",0);
                startActivity(i);
            }
        });

    }
}