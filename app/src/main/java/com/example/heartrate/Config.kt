package com.example.heartrate

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import com.example.heartrate.databinding.ActivityConfigBinding

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat

class Config : AppCompatActivity() {
    lateinit var binding : ActivityConfigBinding
    var m_bluetootAdapter : BluetoothAdapter? = null

    val REQUEST_ENABLE_BLUETOOH = 1
    val REQUEST_BLUETOOTH_CONNECT_PERMISSION = 2
    companion object{
        val EXTRA_ADDRESS: String = "Device_address"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityConfigBinding.inflate(layoutInflater)
       setContentView(binding.root)
        val mostrarDispositivos = findViewById<Button>(R.id.buscarBluetooth)
        mostrarDispositivos.setOnClickListener {
            searchPairedDevices()
        }
        val Permisos = findViewById<Button>(R.id.permiso)
        Permisos.setOnClickListener {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_CONNECT), REQUEST_BLUETOOTH_CONNECT_PERMISSION)
        }
        val editarDatosPer = findViewById<Button>(R.id.edit_info_button)
        editarDatosPer.setOnClickListener {
            val valor = "1"
            val intent = Intent (this,EditarDatos::class.java)
            intent.putExtra("valor", valor)
            startActivity(intent)
        }

        //Primero Obtenemos el bluetooth adapter
        m_bluetootAdapter = BluetoothAdapter.getDefaultAdapter()
        if(m_bluetootAdapter == null){
            Toast.makeText(this, "Este dispositivo no soporta bluetooth", Toast.LENGTH_SHORT).show()
            return
        }
        else{
            Toast.makeText(this, "Este dispositivo si soporta bluetooth", Toast.LENGTH_SHORT).show()
        }

        //Luego habilitamos el bluetooth
        if(m_bluetootAdapter?.isEnabled == false) {//Si esto se cumple, significa que no está habilitado
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOH)
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_CONNECT), REQUEST_BLUETOOTH_CONNECT_PERMISSION)
        } else {
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_BLUETOOTH_CONNECT_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "El permiso ha sido aprobado", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "El permiso ha sido denegado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun searchPairedDevices() {
        if (REQUEST_BLUETOOTH_CONNECT_PERMISSION==1||REQUEST_BLUETOOTH_CONNECT_PERMISSION==2){
            var m_pairedDevices : Set<BluetoothDevice> ? = m_bluetootAdapter?.bondedDevices
            m_pairedDevices?.forEach { device ->
                val deviceName = device.name
                Toast.makeText(this, "Dispositivo: $deviceName", Toast.LENGTH_LONG).show()
                val deviceHardwareAddress = device.address // MAC address
                Toast.makeText(this, "Dirección MAC: $deviceHardwareAddress", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "El permiso ha sido denegado", Toast.LENGTH_SHORT).show()

        }

    }
}