package com.diazp.catchit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.diazp.catchit.fragment.FavoritFragment
import com.diazp.catchit.fragment.HomeFragment
import com.diazp.catchit.fragment.JadwalFragment
import com.diazp.catchit.fragment.NotifikasiFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val FragmentHome: Fragment = HomeFragment()
    private val FragmentFavorit: Fragment = FavoritFragment()
    private val FragmentNotifikasi: Fragment = NotifikasiFragment()
    private val FragmentJadwal: Fragment = JadwalFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = FragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NyiapinBottomNav()
    }

    fun NyiapinBottomNav() {
        fm.beginTransaction().add(R.id.frame_layout_container, FragmentHome).show(FragmentHome)
            .commit()
        fm.beginTransaction().add(R.id.frame_layout_container, FragmentFavorit)
            .hide(FragmentFavorit).commit()
        fm.beginTransaction().add(R.id.frame_layout_container, FragmentNotifikasi)
            .hide(FragmentNotifikasi).commit()
        fm.beginTransaction().add(R.id.frame_layout_container, FragmentJadwal).hide(FragmentJadwal)
            .commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_home -> {
                    GantiFragment(0, FragmentHome)
                }

                R.id.navigation_favorit -> {
                    GantiFragment(1, FragmentFavorit)
                }

                R.id.navigation_notifikasi -> {
                    GantiFragment(2, FragmentNotifikasi)
                }

                R.id.navigation_jadwal -> {
                    GantiFragment(3, FragmentJadwal)
                }
            }

            false
        }
    }

    fun GantiFragment(nomor: Int, fragment: Fragment) {
        menuItem = menu.getItem(nomor)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}