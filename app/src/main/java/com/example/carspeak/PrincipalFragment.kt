package com.example.carspeak

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carspeak.util.BluetoothJhr
import kotlinx.android.synthetic.main.fragment_principal.*
import pl.droidsonroids.gif.AnimationListener
import pl.droidsonroids.gif.GifDrawable
import java.util.*
import kotlin.concurrent.thread


class PrincipalFragment : Fragment(), AnimationListener {

    private lateinit var viewOfLayout: View
    private lateinit var gifDrawable: GifDrawable
    private var timer: Timer = Timer()
    lateinit var blue :BluetoothJhr
    var initConexion = false
    var offHilo = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_principal, container, false)

        return viewOfLayout
    }

    private fun resetAnimation() {
        gifDrawable.stop()
        gifDrawable.seekToFrameAndGet(5)
        timer.purge()
    }

    private fun letterAnimation() {
        timer = Timer()
            val showDot1 = object : TimerTask() {
                override fun run() {
                    txtEscuchando.text = "Escuchando ."
                }
            }
            val showDot2 = object : TimerTask() {
                override fun run() {
                    txtEscuchando.text = "Escuchando . ."
                }
            }
            val showDot3 = object : TimerTask() {
                override fun run() {
                    txtEscuchando.text = "Escuchando . . ."
                }
            }
            val resetDots = object : TimerTask() {
                override fun run() {
                    txtEscuchando.text = "Escuchando"
                }
            }
            timer.schedule(showDot1, 1000, 6000)
            timer.schedule(showDot2, 2000, 6000)
            timer.schedule(showDot3, 3000, 6000)
            timer.schedule(resetDots, 5000, 6000)
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
        ///BLUETOOTH PART
        blue = BluetoothJhr(view.context, DispositivosVinculados::class.java)
        //si se pierde conexion no sale sino que avisa con un mensaje  error
        blue.exitErrorOk(true)
        //mensaje conectado
        blue.mensajeConexion("Conectado jhr")
        //mensaje de error
        blue.mensajeErrorTx("algo salio mal")

        thread (start = true){
            while (!initConexion && !offHilo){
                Thread.sleep(500)
            }

            while (!offHilo){
                var mensaje = blue.mRx()
                if (mensaje!=""){

                }
                Thread.sleep(100)
            }

        }



        btnPrueba2.setOnClickListener {
            blue.mTx("ola")
        }


        btnPrueba2.setOnLongClickListener {
            blue.exitConexion()
            offHilo = true
            false
        }
    }

    override fun onAnimationCompleted(loopNumber: Int) {
        val view = view
        if (view != null) {
        }
    }

    override fun onResume() {
        initConexion =  blue.conectaBluetooth()
        super.onResume()
    }

    override fun onPause() {
        offHilo = true
        blue.exitConexion()
        super.onPause()
    }

}