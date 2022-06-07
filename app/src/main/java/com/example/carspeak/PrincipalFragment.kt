package com.example.carspeak

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_principal.*


class PrincipalFragment : Fragment() {

    private lateinit var viewOfLayout: View
    private var handlerAnimation = Handler()
    private var statusAnimation = false
    private lateinit var button: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewOfLayout = inflater!!.inflate(R.layout.fragment_principal, container, false)

        button = viewOfLayout.findViewById(R.id.button)

        button.setOnClickListener {
            if (statusAnimation){
                stopPulse()
                button.text = "Iniciar"
            }
            else {
                startPulse()
                button.text = "Detener"
            }
            statusAnimation = !statusAnimation
        }
        return viewOfLayout
    }

    private fun startPulse() {
        runnable.run()
    }

    private fun stopPulse() {
        Log.e("arrrweee", " parar")
        handlerAnimation.removeCallbacks(runnable)
    }

    private var runnable = object : Runnable {
        override fun run() {

            imgAnimation1.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(700)
                .withEndAction {
                    imgAnimation1.scaleX = 1f
                    imgAnimation1.scaleY = 1f
                    imgAnimation1.alpha = 1f
                }

            imgAnimation2.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(400)
                .withEndAction {
                    imgAnimation2.scaleX = 1f
                    imgAnimation2.scaleY = 1f
                    imgAnimation2.alpha = 1f
                }

            handlerAnimation.postDelayed(this, 1500)
        }
    }

}