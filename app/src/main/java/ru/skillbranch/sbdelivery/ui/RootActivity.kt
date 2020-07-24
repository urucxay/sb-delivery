package ru.skillbranch.sbdelivery.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.sbdelivery.R

class MainActivity : AppCompatActivity(R.layout.activity_root) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SBDelivery_NoActionBar)
        super.onCreate(savedInstanceState)
    }

}
