package com.example.vnews.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.vnews.R
import com.example.vnews.adapterJ.MainVpAdapter
import com.example.vnews.adapterJ.MenuRightAdapter
import com.example.vnews.fragmentJ.*
import com.example.vnews.modelJ.MenuRight
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var nvMenu: NavigationView
    lateinit var drlayout: DrawerLayout
    lateinit var etFind: EditText

    lateinit var arrayMenu: ArrayList<MenuRight>
    lateinit var menuRightAdapter: MenuRightAdapter
    lateinit var listViewMenu: ListView
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

        GetMenuLeft()
    }

    private fun GetMenuLeft() {
        arrayMenu.add(MenuRight("Di động", "https://img.icons8.com/wired/64/000000/multiple-smartphones.png"))
        arrayMenu.add(MenuRight("Máy tính", "https://img.icons8.com/pastel-glyph/64/000000/laptop-computer--v2.png"))
        arrayMenu.add(MenuRight("Thiết bị công nghệ", "https://img.icons8.com/ios/64/000000/wearable-technology.png"))
        arrayMenu.add(MenuRight("Đời sống cộng nghệ", "https://img.icons8.com/dotty/50/000000/technology-lifestyle.png"))
        arrayMenu.add(MenuRight("Thủ thuật", "https://img.icons8.com/ios/80/000000/magical-scroll.png"))
        arrayMenu.add(MenuRight("Game", "https://img.icons8.com/pastel-glyph/50/000000/controller--v2.png"))
    }

    private fun anhXa() {
        tabLayout = findViewById(R.id.mytablayout)
        viewPager = findViewById(R.id.myviewpager)
        nvMenu = findViewById(R.id.nvMenu)
        drlayout = findViewById(R.id.drawerlayout)
        etFind = findViewById(R.id.etFind)

        listViewMenu = findViewById(R.id.listviewMenu)
        arrayMenu = ArrayList()
        menuRightAdapter = MenuRightAdapter(arrayMenu, applicationContext)
        listViewMenu.setAdapter(menuRightAdapter)
    }

    fun openMenu(view: View?) {
        drlayout.openDrawer(GravityCompat.END)
    }
}

