package com.example.navigationdrawer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 1. Find the views we created in the XML
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.navigation_view)
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)

        // 2. Get the Navigation Controller
        // The NavController is the engine that drives swapping the Fragments
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // 3. Define your top-level screens
        // Passing the drawerLayout here tells Android to show the "Hamburger" menu icon
        // instead of a "Back" arrow on these screens.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_broadcast,
                R.id.nav_image_scale,
                R.id.nav_video,
                R.id.nav_audio
            ), drawerLayout
        )

        // 4. Wire everything together!
        // This makes the Toolbar show the correct title and open the drawer when clicked
        toolbar.setupWithNavController(navController, appBarConfiguration)

        // This makes the side menu swap the fragments when you click an item
        navView.setupWithNavController(navController)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}