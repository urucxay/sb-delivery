package ru.skillbranch.sbdelivery.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.mikepenz.materialdrawer.holder.DimenHolder
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.NavigationDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.iconRes
import com.mikepenz.materialdrawer.util.*
import kotlinx.android.synthetic.main.activity_root.*
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.utils.dpToPx
import ru.skillbranch.sbdelivery.utils.inflateNavigationMenu

class RootActivity : AppCompatActivity(R.layout.activity_root) {

    private lateinit var appBarConfiguration: AppBarConfiguration

    @ExperimentalNavController
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SBDelivery_NoActionBar)
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeDestination,
            R.id.menuDestination,
            R.id.favoriteDestination,
            R.id.cartDestination,
            R.id.profileDestination,
            R.id.ordersDestination,
            R.id.notificationDestination,
            R.id.aboutDestination
        ), drawerLayout)
        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //setup navigation drawer
        with(navView) {
            //set drawer width
            customWidth = context.dpToPx(300).toInt()

            //set header
            headerView = layoutInflater.inflate(R.layout.nav_drawer_header, null)
            headerHeight = DimenHolder.fromDp(170)
            headerDivider = false

            //set base menu
            inflateNavigationMenu(R.menu.drawer_menu)

            //set footer
            stickyFooterShadow = false
            val aboutMenuItem = PrimaryDrawerItem().apply {
                iconRes = R.drawable.ic_about
                name = StringHolder(R.string.drawer_title_about)
            }
            navView.addStickyFooterItem(NavigationDrawerItem(R.id.aboutDestination, aboutMenuItem))
        }

    }

    //override navigeteUp for navController
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    //close drawer when back pressed
    override fun onBackPressed() {
        if (navView.drawerLayout != null && navView.drawerLayout!!.isOpen) {
            navView.drawerLayout?.closeDrawer(navView)
        } else {
            super.onBackPressed()
        }
    }

}
