package ru.skillbranch.sbdelivery.ui.activity.root

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.mikepenz.materialdrawer.holder.DimenHolder
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.NavigationDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.iconRes
import com.mikepenz.materialdrawer.util.ExperimentalNavController
import com.mikepenz.materialdrawer.util.addStickyFooterItem
import com.mikepenz.materialdrawer.util.setupWithNavController
import kotlinx.android.synthetic.main.activity_root.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import ru.skillbranch.sbdelivery.R
import ru.skillbranch.sbdelivery.ui.base.BaseActivity
import ru.skillbranch.sbdelivery.ui.base.IViewModelState
import ru.skillbranch.sbdelivery.ui.base.Notification
import ru.skillbranch.sbdelivery.util.dpToPx
import ru.skillbranch.sbdelivery.util.inflateNavigationMenu

class RootActivity : BaseActivity<RootViewModel>() {

    override val layout: Int = R.layout.activity_root
    public override val viewModel: RootViewModel by stateViewModel()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val topLevelDestinations = setOf(
        R.id.homeDestination,
        R.id.menuDestination,
        R.id.favoriteDestination,
        R.id.cartDestination,
        R.id.profileDestination,
        R.id.ordersDestination,
        R.id.notificationDestination,
        R.id.aboutDestination
    )

    @ExperimentalNavController
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SBDelivery_NoActionBar)
        super.onCreate(savedInstanceState)

        //set top level destinations
        appBarConfiguration = AppBarConfiguration(topLevelDestinations, drawerLayout)
        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //setup navigation drawer
        with(navView) {
            //set drawer width
            customWidth = context.dpToPx(300).toInt()
            //set header
            headerView = layoutInflater.inflate(R.layout.nav_drawer_header, container, false)
            headerHeight = DimenHolder.fromDp(170)
            headerDivider = false

            //set base menu
            inflateNavigationMenu(R.menu.drawer_menu)
            if (navController.currentDestination?.id == R.id.homeDestination) {
                setSelectionAtPosition(1)
            }

            //set footer
            stickyFooterShadow = false
            val aboutMenuItem = PrimaryDrawerItem().apply {
                iconRes = R.drawable.ic_about
                name = StringHolder(R.string.drawer_title_about)
            }
            addStickyFooterItem(NavigationDrawerItem(R.id.aboutDestination, aboutMenuItem))
        }

    }

    //override navigeteUp for navController
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @SuppressLint("RestrictedApi")
    override fun onBackPressed() {
        if (navView.drawerLayout!!.isOpen) {
            navView.drawerLayout?.closeDrawer(navView) //close drawer if it's opened
        } else {
            if (topLevelDestinations.contains(navController.currentDestination?.id)
//                && navController.currentDestination?.id != R.id.homeDestination
            ) {
//                navController.popBackStack(R.id.homeDestination, false)
                finish() //close app if current fragment is top destination fragment
            } else {
                super.onBackPressed()
                navView.setSelectionAtPosition(navView.selectedItemPosition)
            }
        }
    }

    override fun renderNotification(notification: Notification) {
        val snackbar = Snackbar.make(container, notification.message, Snackbar.LENGTH_LONG)

        when (notification) {
            is Notification.TextMessage -> {
            }
            is Notification.ActionMessage -> {
                val (_, label, handler) = notification
                with(snackbar) {
                    setActionTextColor(getColor(R.color.color_secondary))
                    setAction(label) { handler.invoke() }
                }
            }
            is Notification.ErrorMessage -> {
                val (_, label, handler) = notification
                with(snackbar) {
                    setBackgroundTint(getColor(R.color.design_default_color_error))
                    setTextColor(getColor(android.R.color.white))
                    setActionTextColor(getColor(android.R.color.white))
                    handler ?: return@with
                    setAction(label) { handler.invoke() }
                }
            }
        }
        snackbar.show()
    }


    override fun subscribeOnState(state: IViewModelState) {
    }

}
