package ru.skillbranch.sbdelivery.utils

import android.annotation.SuppressLint
import android.view.Menu
import androidx.annotation.MenuRes
import androidx.appcompat.view.SupportMenuInflater
import androidx.appcompat.view.menu.MenuBuilder
import com.mikepenz.materialdrawer.holder.ImageHolder
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.NavigationDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.ExperimentalNavController
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView

@ExperimentalNavController
@SuppressLint("RestrictedApi")
fun MaterialDrawerSliderView.inflateNavigationMenu(@MenuRes menuRes: Int) {
    val menuInflater = SupportMenuInflater(context)
    val mMenu = MenuBuilder(context)

    menuInflater.inflate(menuRes, mMenu)

    addNavigatableMenuItems(mMenu)
}

@ExperimentalNavController
private fun MaterialDrawerSliderView.addNavigatableMenuItems(mMenu: Menu) {
    var iDrawerItem: IDrawerItem<*>
    for (i in 0 until mMenu.size()) {
        val mMenuItem = mMenu.getItem(i)
        iDrawerItem = PrimaryDrawerItem().apply {
            name = StringHolder(mMenuItem.title)
            icon = ImageHolder(mMenuItem.icon)
            identifier = mMenuItem.itemId.toLong()
        }
        itemAdapter.add(NavigationDrawerItem(mMenuItem.itemId, iDrawerItem))
    }
}