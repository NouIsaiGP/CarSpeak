package com.example.carspeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.carspeak.util.BluetoothJhr
import com.example.carspeak.util.ViewPagerAdapter
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var blue :BluetoothJhr
    var initConexion = false
    var offHilo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val fragments: ArrayList<Fragment> = arrayListOf(
            PrincipalFragment(),
            BotonesFragment()
        )

        val adapter = ViewPagerAdapter(fragments,this)
        viewPager.adapter = adapter


    }

}