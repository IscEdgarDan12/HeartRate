package com.example.heartrate


import android.content.Intent
import android.graphics.Color

import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.telephony.SmsManager
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class Mediciones : AppCompatActivity() {

    private lateinit var graphView: GraphView
    private lateinit var series: LineGraphSeries<DataPoint>
    private lateinit var handler: Handler
    private var lastX = 0.0
    private var lastY = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediciones)
        val baja = findViewById<Button>(R.id.preBaja)
        baja.setOnClickListener {
            preBajaButton()
        }
        val alta = findViewById<Button>(R.id.preAlta)
        alta.setOnClickListener {
            preAltaButton()
        }
        // Configurar la vista de la gráfica
        graphView = findViewById(R.id.graph)
        graphView.title = getString(R.string.mediciones)
        graphView.titleColor = Color.BLACK
        //graphView.gridLabelRenderer.horizontalAxisTitle = getString(R.string.time_axis_label)
        //graphView.gridLabelRenderer.verticalAxisTitle = getString(R.string.bpm_axis_label)
        graphView.viewport.isXAxisBoundsManual = true
        graphView.viewport.isYAxisBoundsManual = true
        graphView.viewport.setMinX(0.0)
        graphView.viewport.setMaxX(10.0)
        graphView.viewport.setMinY(0.0)
        graphView.viewport.setMaxY(200.0)

        // Configurar la serie de datos de la gráfica
        series = LineGraphSeries<DataPoint>()
        series.color = Color.RED
        series.thickness = 3

        // Añadir la serie a la gráfica
        graphView.addSeries(series)

        // Iniciar la actualización de los datos de la gráfica
        handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                updateGraphData()
                handler.postDelayed(this, 3000)
            }
        }, 10000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    private fun updateGraphData() {
        // Agregar un nuevo punto a la serie
        val x = lastX + 1
        val y = lastX +1
        series.appendData(DataPoint(x, y), true, 10)

        // Actualizar el valor de lastX
        lastX = x
    }
    private fun preBajaButton(){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val telefonoDr = sharedPreferences.getString("numeroDoc", null)
        val numero = telefonoDr
        val mensaje = "Me siento mal favor de venir a: TecNM"
        val sms = SmsManager.getDefault()
        sms.sendTextMessage(numero, null, mensaje, null, null)
        Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show()

    }
    private fun preAltaButton(){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val telefonoDr = sharedPreferences.getString("numeroDoc", null)
        val numero = telefonoDr
        val mensaje = "Me siento mal favor de venir a: TecNM"
        val sms = SmsManager.getDefault()
        sms.sendTextMessage(numero, null, mensaje, null, null)
        Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show()
    }
   }


