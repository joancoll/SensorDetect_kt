package cat.dam.andy.sensordetect_kt

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * SensorDetect utilitza SensorManager per a obtenir la llista
 * de sensors disponibles, i mostra la llista com a TextView.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var tv_sensors: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        showSensorList()
    }

    private fun initViews() {
        tv_sensors = findViewById(R.id.tv_sensors)
    }

    private fun showSensorList() {
        var nSensors = 0 //quantitat de sensors
        //getSystemService retorna un handler (gestor) d'un servei del sistema
        //amb getSystemService podem accedir no només als sensors, també al gestor de bateria, vibració, alarma, ....
        //En aquest cas el handler serà del tipus SensorManager i connectarà amb el servei de sensors
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        //cridem el mètode .getSensorList que obté la llista de tots els sensors disponibles (tipus Sensor)
        //podriem triar de quin tipus volem comprovar p.e. TYPE_AMBIENT_TEMPERATURE
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)
        //Itera la llista de sensors, obté nom del sensor i l'afegeix a un string amb un salt de línia.
        //Utilitzem StringBuilder enlloc de concat o + per rendiment ja que el compilador crearà menys objectes
        val textSensors = StringBuilder()
        for (currentSensor in sensorList) {
            nSensors++
            textSensors.append(nSensors).append("-").append(currentSensor.name).append(
                System.getProperty("line.separator")
            ) //No depèn SO (\n Linux o \r\n Windows)
        }
        // Actualitza el textview amb la llista de sensors disponibles del dispositiu.
        tv_sensors.text = textSensors
    }
}