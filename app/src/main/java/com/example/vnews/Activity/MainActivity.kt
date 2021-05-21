package com.example.vnews.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.vnews.R
import com.example.vnews.adapterJ.MainVpAdapter
import com.example.vnews.fragmentJ.*
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var nvMenu: NavigationView
    lateinit var drlayout: DrawerLayout
    lateinit var etFind: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        anhXa()
        init()
    }

    private fun init() {
        var mainVpAdapter : MainVpAdapter =
            MainVpAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        mainVpAdapter.addFragment(FragmentHome(), "")
        mainVpAdapter.addFragment(FragmentTechnical(), "")
        mainVpAdapter.addFragment(FragmentHotnews(), "")
        mainVpAdapter.addFragment(FragmentKhampha(), "")
        mainVpAdapter.addFragment(FragmentProfile(), "")
        viewPager.setAdapter(mainVpAdapter)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_home)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_tech)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_fire)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_compass)
        tabLayout.getTabAt(4)?.setIcon(R.drawable.ic_user)

        etFind.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun anhXa() {
        tabLayout = findViewById(R.id.mytablayout)
        viewPager = findViewById(R.id.myviewpager)
        nvMenu = findViewById(R.id.nvMenu)
        drlayout = findViewById(R.id.drawerlayout)
        etFind = findViewById(R.id.etFind)
    }

    fun openMenu(view: View?) {
        drlayout.openDrawer(GravityCompat.END)

    }
}

