package com.example.vnews.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.vnews.R;
import com.example.vnews.adapterJ.FragmentAdapter;
import com.example.vnews.fragmentJ.FragmentLogin;
import com.example.vnews.fragmentJ.FragmentRegister;
import com.google.android.material.tabs.TabLayout;

public class LogRegActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);
        anhXa();
        init();
    }

    private void anhXa() {
        tabLayout = findViewById(R.id.mytablayout);
        viewPager = findViewById(R.id.myviewpager);
    }


    private void init() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentAdapter.addFragment(new FragmentLogin(), "Đăng nhập");
        fragmentAdapter.addFragment(new FragmentRegister(), "Đăng ký");
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void returnHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivities(new Intent[]{intent});
    }
}