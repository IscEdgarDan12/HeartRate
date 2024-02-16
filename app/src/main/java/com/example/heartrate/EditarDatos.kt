package com.example.heartrate

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText


class EditarDatos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_datos)

        cambiarVista()
    }

    private fun GuardarDatos() {
        val nombrePaView = findViewById<EditText>(R.id.nombreP)
        val ellipsoidal = findViewById<EditText>(R.id.apellidosPa)
        val directionPa = findViewById<EditText>(R.id.direccionPa)
        val numerousPa = findViewById<EditText>(R.id.numeroPa)
        val nombreDoc = findViewById<EditText>(R.id.nombreDoc)
        val direccionDoc = findViewById<EditText>(R.id.direccionDoc)
        val numeroDoc = findViewById<EditText>(R.id.numeroDoc)


        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        editor.putString("nombre", nombrePaView.text.toString())
        editor.putString("apellido", ellipsoidal.text.toString())
        editor.putString("direccion", directionPa.text.toString())
        editor.putString("numero", numerousPa.text.toString())
        editor.putString("nombreDoc", nombreDoc.text.toString())
        editor.putString("direccionDoc", direccionDoc.text.toString())
        editor.putString("numeroDoc", numeroDoc.text.toString())

        editor.apply()
    }


    private fun cambiarVista() {
        val guardarInf = findViewById<Button>(R.id.guardarInf)
        guardarInf.setOnClickListener {
            val intent = Intent(this@EditarDatos, Datos::class.java)
            startActivity(intent)
            GuardarDatos()
            finish()
        }

    }
}