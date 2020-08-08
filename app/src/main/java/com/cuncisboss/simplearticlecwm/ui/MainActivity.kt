package com.cuncisboss.simplearticlecwm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cuncisboss.simplearticlecwm.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation_view.setupWithNavController(nav_host_fragment.findNavController())

        nav_host_fragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment, R.id.createFragment, R.id.accountFragment -> {
                        tv_toolbar_title.text = getString(R.string.app_name)
                        bottom_navigation_view.visibility = View.VISIBLE
                    }
                    R.id.loginFragment -> {
                        tv_toolbar_title.text = getString(R.string.login)
                        bottom_navigation_view.visibility = View.GONE
                    }
                    R.id.registerFragment -> {
                        tv_toolbar_title.text = getString(R.string.register)
                        bottom_navigation_view.visibility = View.GONE
                    }
                    else -> bottom_navigation_view.visibility = View.GONE
                }
            }
    }
}