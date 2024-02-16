package com.example.heartrate
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.heartrate.databinding.ActivityDatosBinding
import com.example.heartrate.databinding.ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_data.*

class Datos : AppCompatActivity() {

    lateinit var binding : ActivityDatosBinding
    lateinit var bindingActivityMain : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setContentView(R.layout.activity_datos)

            val NombreC : TextView = findViewById(R.id.NombreC)
            val ApellidosC : TextView = findViewById(R.id.ApellidosC)
            val DireccionC : TextView = findViewById(R.id.DireccionC)
            val TelefonoC : TextView = findViewById(R.id.TelefonoC)
            val NombreDr : TextView = findViewById(R.id.NombreDr)
            val DireccionDr : TextView = findViewById(R.id.DireccionDr)
            val TelefonoDr : TextView = findViewById(R.id.TelefonoDr)

            // Obtener una referencia al objeto SharedPreferences
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@Datos)

            // Recuperar los datos guardados
            val nombreC = sharedPreferences.getString("nombre", null)
            val apellidoC = sharedPreferences.getString("apellido", null)
            val direccionC = sharedPreferences.getString("direccion", null)
            val numeroC = sharedPreferences.getString("numero", null)
            val nombreDr = sharedPreferences.getString("nombreDoc", null)
            val direccionDr = sharedPreferences.getString("direccionDoc", null)
            val telefonoDr = sharedPreferences.getString("numeroDoc", null)


            NombreC.setText("Nombre(s): "+nombreC.toString())
            ApellidosC.setText("Apellidos: "+apellidoC.toString())
            DireccionC.setText("Dirección: "+direccionC.toString())
            TelefonoC.setText("Número: "+numeroC.toString())

            NombreDr.setText("Nombre(s): "+nombreDr.toString())
            DireccionDr.setText("Dirección: "+direccionDr.toString())
            TelefonoDr.setText("Número: "+telefonoDr.toString())

            val btnMainActivity: Button = findViewById(R.id.irMenu)
            btnMainActivity.setOnClickListener {
                val intent = Intent(this@Datos, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            val editar: Button = findViewById(R.id.editar)
            editar.setOnClickListener {
                val intent = Intent(this@Datos, EditarDatos::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
