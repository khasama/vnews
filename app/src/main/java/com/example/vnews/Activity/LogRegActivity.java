package com.example.vnews.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.vnews.R;

public class LogRegActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);
        initPreferences();
        init();
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
    }

    private void init() {
    }

    public void returnHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivities(new Intent[]{intent});
    }
}