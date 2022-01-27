package com.diazp.catchit

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.diazp.catchit.fragment.FavoritFragment
import com.diazp.catchit.fragment.HomeFragment
import com.diazp.catchit.fragment.KeranjangFragment
import com.diazp.catchit.fragment.NotifikasiFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.channels.BroadcastChannel

class MainActivity : AppCompatActivity() {
    private val FragmentHome: Fragment = HomeFragment()
    private val FragmentFavorit: Fragment = FavoritFragment()
    private val FragmentNotifikasi: Fragment = NotifikasiFragment()
    private val FragmentKeranjang: Fragment = KeranjangFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = FragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private var dariDetails: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NyiapinBottomNav()

        LocalBroadcastManager.getInstance(this).registerReceiver(message, IntentFilter("event:keranjang"))
    }

    val message: BroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            dariDetails = true
        }
    }

    fun NyiapinBottomNav() {
        fm.beginTransaction().add(R.id.frame_layout_container, FragmentHome).show(FragmentHome)
            .commit()
        fm.beginTransaction().add(R.id.frame_layout_container, FragmentFavorit)
            .hide(FragmentFavorit).commit()
        fm.beginTransaction().add(R.id.frame_layout_container, FragmentNotifikasi)
            .hide(FragmentNotifikasi).commit()
        fm.beginTransaction().add(R.id.frame_layout_container, FragmentKeranjang).hide(FragmentKeranjang)
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

                R.id.navigation_keranjang -> {
                    GantiFragment(3, FragmentKeranjang)
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

    override fun onResume() {
        if (dariDetails) {
            dariDetails = false
            GantiFragment(3, FragmentKeranjang)
        }
        super.onResume()
    }
}