package com.example.adaptertest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class XMLMenuActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlmenu);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.xmlText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.size1){
            editText.setTextSize(10);
        }else if(id == R.id.size2){
            editText.setTextSize(16);
        }else if(id == R.id.size3){
            editText.setTextSize(20);
        }else if(id == R.id.xmlMenu_normal_menu){
            Toast.makeText(XMLMenuActivity.this, "普通菜单项", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.color1){
            editText.setTextColor(Color.RED);
        }else if(id == R.id.color2){
            editText.setTextColor(Color.BLACK);
        }
        return true;
    }
}