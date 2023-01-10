package kr.co.yeeun.lee.demoi.testbottomnavigation

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import kr.co.yeeun.lee.demoi.testbottomnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.main_nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController

        setToolbar()
        setBottomNavigation()
    }

    override fun onBackPressed() {
        if (binding.mainDrawer.isDrawerOpen(Gravity.LEFT))
            binding.mainDrawer.closeDrawer(Gravity.LEFT)
        else
            super.onBackPressed()
    }

    private fun setToolbar(){
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        binding.navView.setupWithNavController(navController)

        binding.toolbar.setNavigationOnClickListener {
            changeDrawer()
        }
    }

    private fun changeDrawer(){
        binding.apply {
            if(!mainDrawer.isDrawerOpen(Gravity.LEFT)){
                mainDrawer.openDrawer(Gravity.LEFT)

            }else {
                mainDrawer.closeDrawer(Gravity.LEFT)
            }
        }
    }

    private fun setBottomNavigation(){
        binding.bottomNav.setupWithNavController(navController)
    }
}