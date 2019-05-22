package com.hjalmarsson.fashion

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hjalmarsson.fashion.util.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(MainViewModel::class.java)

        nav_bar.layoutParams.height = Util.getNavigationBarHeight(this)
        nav_bar.elevation = navigation.elevation

        navigation.setupWithNavController(nav_host_fragment.findNavController())

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            vm.updateDestinationId(destination.id)
            navigation.visibility = if (vm.shouldShowNavigation()) View.VISIBLE else View.GONE
        }
    }
}
