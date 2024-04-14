package com.example.a22it343_nguynnhanhv

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, Activity3()).commit()
            navigationView.setCheckedItem(R.id.nav_class)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_exit -> {
                finish()
                return true
            }
            else -> {
                val manager = supportFragmentManager
                var fragment = manager.findFragmentByTag(item.itemId.toString())
                val transaction = manager.beginTransaction()
                if (fragment == null) {
                    fragment = when (item.itemId) {
                        R.id.nav_prime -> Activity1()
                        R.id.nav_equation -> Activity2()
                        R.id.nav_class -> Activity3()
                        else -> null
                    }
                    if (fragment != null) {
                        transaction.add(R.id.fragment_container, fragment, item.itemId.toString())
                    }
                } else {
                    transaction.show(fragment)
                }

                manager.fragments.forEach { if (it != fragment) transaction.hide(it) }
                transaction.commit()
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
        }
    }

    @Deprecated("Navigation")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
