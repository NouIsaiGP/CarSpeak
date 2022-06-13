package com.example.carspeak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_principal.*
import pl.droidsonroids.gif.AnimationListener
import pl.droidsonroids.gif.GifDrawable
import java.util.*


class PrincipalFragment : Fragment(), AnimationListener {

    private lateinit var viewOfLayout: View
    private lateinit var gifDrawable: GifDrawable
    private var timer: Timer = Timer()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_principal, container, false)

        return viewOfLayout
    }

    private fun resetAnimation() {
        gifDrawable.stop()
        gifDrawable.seekToFrameAndGet(5)
        inivsibleTextDots()
        timer.purge()
    }

    private fun letterAnimation() {
        timer = Timer()

        val showDot1 = object : TimerTask() {
            override fun run() {
                txtPunto1.visibility = View.VISIBLE
            }
        }
        val showDot2 = object : TimerTask() {
            override fun run() {
                txtPunto2.visibility = View.VISIBLE
            }
        }
        val showDot3 = object : TimerTask() {
            override fun run() {
                txtPunto3.visibility = View.VISIBLE
            }
        }
        val resetDots = object : TimerTask() {
            override fun run() {
                inivsibleTextDots()
            }
        }
        timer.schedule(showDot1, 1000, 6000)
        timer.schedule(showDot2, 2000, 6000)
        timer.schedule(showDot3, 3000, 6000)
        timer.schedule(resetDots, 5000, 6000)
        /*for (i in 1..5) {
            Handler(Looper.getMainLooper()).postDelayed({
                txtPunto1.visibility = View.VISIBLE
            }, 1000)

            Handler(Looper.getMainLooper()).postDelayed({
                txtPunto2.visibility = View.VISIBLE
            }, 2000)

            Handler(Looper.getMainLooper()).postDelayed({
                txtPunto3.visibility = View.VISIBLE
            }, 3000)

            Handler(Looper.getMainLooper()).postDelayed({
                txtPunto1.visibility = View.INVISIBLE
                txtPunto2.visibility = View.INVISIBLE
                txtPunto3.visibility = View.INVISIBLE
            }, 4000)
        }*/
    }

    private fun inivsibleTextDots(){
        txtPunto1.visibility = View.INVISIBLE
        txtPunto2.visibility = View.INVISIBLE
        txtPunto3.visibility = View.INVISIBLE
    }

    private fun toggleAnimation() = when {
        gifDrawable.isPlaying -> gifDrawable.stop().also {
            txtEscuchando.visibility = View.INVISIBLE
            timer.cancel()
            resetAnimation() }
        else -> gifDrawable.start().also {
            txtEscuchando.visibility = View.VISIBLE
            letterAnimation()}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEscuchar.setOnClickListener { toggleAnimation() }
        gifDrawable = btnGif.drawable as GifDrawable

        resetAnimation()
        gifDrawable.addAnimationListener(this)
    }

    override fun onAnimationCompleted(loopNumber: Int) {
        val view = view
        if (view != null) {
        }
    }

}