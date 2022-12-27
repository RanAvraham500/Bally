package com.example.bally.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bally.R;
import com.example.bally.levels.LevelsActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStore, btnLevels, btnTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnStore = findViewById(R.id.btnStore);
        btnStore.setOnClickListener(this);
        btnLevels = findViewById(R.id.btnLevels);
        btnLevels.setOnClickListener(this);
        btnTable = findViewById(R.id.btnTable);
        btnTable.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnStore.getId()) {
            //go to store activity
        } else if (v.getId() == btnLevels.getId()) {
            Intent intent = new Intent(this, LevelsActivity.class);
            startActivity(intent);
        } else {
            //go to score table activity
        }
    }
}