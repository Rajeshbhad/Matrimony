package com.example.marathimatrimony

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.example.marathimatrimony.bottomnavigation.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class NavigationActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener
{

     lateinit var auth: FirebaseAuth


     lateinit var toolbar: Toolbar
     lateinit var navigationView: NavigationView
     lateinit var drawerLayout: DrawerLayout
    lateinit var homeFragment: HomeFragment
    lateinit var matchesFragment: MatchesFragment
    lateinit var mailboxFragment: MailboxFragment
    lateinit var notificationsFragment: NotificationFragment
    lateinit var searchFragment: SearchesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_home -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }
                R.id.navigation_matches ->
                {
                    matchesFragment = MatchesFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,matchesFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }
                R.id.navigation_mailbox ->
                {
                    mailboxFragment = MailboxFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,mailboxFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }
                R.id.navigation_notification ->
                {
                    notificationsFragment = NotificationFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,notificationsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }
                R.id.navigation_searches ->
                {
                    searchFragment = SearchesFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment,searchFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }
            }
            true
        }
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.EditProfile ->{
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent) }

            R.id.EditPreferences ->{
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent) }

            R.id.VerifyYourProfile ->{
                val intent = Intent(this, ProfileVerificationActivity::class.java)
                startActivity(intent) }

            R.id.DailyRecommendation ->{
                val intent = Intent(this, DailyRecommendation::class.java)
                startActivity(intent) }

            R.id.YourChat ->{
                val intent = Intent(this, YourChat::class.java)
                startActivity(intent) }

            R.id.YourCall ->{
                val intent = Intent(this, YourCallActivity::class.java)
                startActivity(intent) }

            R.id.Setting ->{
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent) }

            R.id.Help ->{
                val intent = Intent(this, HelpActivity::class.java)
                startActivity(intent) }

            R.id.WeddingServices ->{
                val intent = Intent(this,WeddingServices::class.java)
                startActivity(intent) }

            R.id.SuccessStories ->{
                val intent = Intent(this, SuccessStoriesActivity::class.java)
                startActivity(intent) }
            R.id.Logout->{
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish() }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
