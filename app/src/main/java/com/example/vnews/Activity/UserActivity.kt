package com.example.vnews.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vnews.Adapter.ViewPageUserAdapter
import com.example.vnews.Fragment.LoginFragment
import com.example.vnews.Fragment.RegisFragment
import com.example.vnews.R
import kotlinx.android.synthetic.main.activity_log_reg.*

class UserActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_reg)
        setUpTab()
    }

    private fun setUpTab(){
        val adapter =ViewPageUserAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(),"Login")
        adapter.addFragment(RegisFragment(),"Sign Up")
        viewPager.adapter = adapter
        tab.setupWithViewPager(viewPager)

        tab.getTabAt(0)!!.setText("Login")
        tab.getTabAt(1)!!.setText("Sign Up")
    }
}