package rs.raf.vezbe11.presentation.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import rs.raf.vezbe11.R

import rs.raf.vezbe11.presentation.view.adapters.CategoryPagerAdapter
import rs.raf.vezbe11.presentation.view.fragments.ContainerFragment
import rs.raf.vezbe11.presentation.view.fragments.IOnBackPressed
import rs.raf.vezbe11.presentation.view.fragments.PlanFragment
import timber.log.Timber

//, NavigationView.OnNavigationItemSelectedListener
class LandingActivity:AppCompatActivity() {
    //private lateinit var binding: ActivityLandingBinding
    private lateinit var drawerLayout: DrawerLayout ;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityLandingBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_landing)

            //var toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, ContainerFragment())
                .addToBackStack(null)
                .commit()
        }
        //rawerLayout = findViewById(R.id.drawer_layout)
        //var navigationView:NavigationView = findViewById(R.id.nav_view)
        //navigationView.setNavigationItemSelectedListener(this)

        //var toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open_nav,R.string.close_nav)
        //drawerLayout.addDrawerListener(toggle)
        //toggle.syncState()


        //if(savedInstanceState==null){
        //    supportFragmentManager.beginTransaction().replace(R.id.fragfrag, PlanFragment()).commit()
         //   navigationView.setCheckedItem(R.id.nav_home)
        //}
        //initRecycler()


    }
/*
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            //!@@@@@@@
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragfrag, PlanFragment()).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }
*/

   // private fun initRecycler() {
   //     binding.viewPager.adapter =
   //         CategoryPagerAdapter(
   //             supportFragmentManager,
   //             this
  //          )
  //      binding.tabLayout.setupWithViewPager(binding.viewPager)
  //  }


}