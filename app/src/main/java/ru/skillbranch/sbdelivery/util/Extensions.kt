package ru.skillbranch.sbdelivery.util

import android.annotation.SuppressLint
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.view.SupportMenuInflater
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
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

fun View.setMarginOptionally(
    left: Int = marginLeft,
    top: Int = marginTop,
    right: Int = marginRight,
    bottom: Int = marginBottom
) {
    (layoutParams as ViewGroup.MarginLayoutParams).setMargins(left, top, right, bottom)
}

fun View.setPaddingOptionally(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}

// source format: 2020-08-03T13:47:43.133Z
fun String.formatApiResponseDate(): String = buildString() {
    if (this@formatApiResponseDate.length >= 10) {
        append(this@formatApiResponseDate.substring(8, 10))
        append(".")
        append(this@formatApiResponseDate.substring(5, 7))
        append(".")
        append(this@formatApiResponseDate.substring(2, 4))
    }
}

