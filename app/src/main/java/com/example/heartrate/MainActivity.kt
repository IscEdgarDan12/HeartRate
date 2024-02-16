package com.example.heartrate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val MY_PERMISSIONS_REQUEST_SEND_SMS = 1

        val configuracion = findViewById<Button>(R.id.configuracion_button)
        configuracion.setOnClickListener {
            val intent = Intent (this,Config::class.java)
            startActivity(intent)

        }
        val datos = findViewById<Button>(R.id.datos_button)
        datos.setOnClickListener {
            val intent = Intent (this,Datos::class.java)
            startActivity(intent)
        }
        val mediciones = findViewById<Button>(R.id.mediciones_button)
        mediciones.setOnClickListener {
            val intent = Intent (this,Mediciones::class.java)
            startActivity(intent)
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS),
                MY_PERMISSIONS_REQUEST_SEND_SMS
            )
        }


    }
}