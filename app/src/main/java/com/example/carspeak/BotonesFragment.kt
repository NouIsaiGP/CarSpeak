package com.example.carspeak

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_botones.*
import kotlinx.android.synthetic.main.fragment_principal.*
import pl.droidsonroids.gif.GifDrawable


class BotonesFragment : Fragment() {

    private lateinit var viewOfLayout: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_botones, container, false)

        return viewOfLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPrueba.setOnClickListener {
            val intent = Intent(context,DispositivosVinculados::class.java)
            startActivity(intent)
        }
    }


}