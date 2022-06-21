package com.example.carspeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.carspeak.util.BluetoothJhr
import kotlinx.android.synthetic.main.activity_dispositivos_vinculados.*


class DispositivosVinculados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispositivos_vinculados)

        val BT = BluetoothJhr(this,listaDispositivos,MainActivity::class.java)

        BT.onBluetooth()

        listaDispositivos.setOnItemClickListener { adapterView, view, i, l ->
            BT.bluetoothSeleccion(i)
        }


    }
}