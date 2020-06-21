package com.daniloosorio.practica1_formulario

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var fecha :String=""
    private var cal =Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
        //////////tomar datos del calendario//////////
    val dateSetListener=
     DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val format= "MM/dd/yyyy"
                val simpleDateFormat=SimpleDateFormat(format,Locale.US)
                fecha=simpleDateFormat.format(cal.time).toString()
                tv_fecha_nacimiento.text=fecha
     }
        ib_calendario.setOnClickListener{
            DatePickerDialog(this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
/////////////////escuchar el click en el boton guardar////////////////////
        bt_guardar.setOnClickListener {
            val nombre = et_nombre.text.toString()
            val cedula = et_cedula.text.toString()
            var telefono =et_telefono.text.toString()
            val correo = et_correo.text.toString()
            val contrasena = et_contrasena.text.toString()
            val Rcontrasena = et_Rcontrasena.text.toString()
            val genero = if(rb_femenino.isChecked)"Femenino" else "Masculino"
            val ciudadnacimiento = sp_ciudad.selectedItem.toString()
            var pasatiempos =""
            if(cb_jugar.isChecked) pasatiempos ="$pasatiempos jugar \n"
            if(cb_leer.isChecked) pasatiempos = "$pasatiempos leer \n"
            if(cb_cine.isChecked) pasatiempos ="$pasatiempos ir a cine \n"
            if(cb_comer.isChecked) pasatiempos = "$pasatiempos comer \n"
///////////////////llamado a funcion para verificar campos vacios y verificacion de contraseñas///////////////////////
            var ver_vacio=vacios(nombre,cedula,correo,telefono,contrasena,Rcontrasena, fecha)
            if(ver_vacio) {
                if (contrasena == Rcontrasena) {
                    tv_resultado.text = "Nombre: $nombre \n" +
                    "Cedula: $cedula \n" +
                    "Correo: $correo \n" +
                    "Genero: $genero \n" +
                    "Ciudad de nacimiento: $ciudadnacimiento \n" +
                    "fecha de nacimiento: $fecha\n"+
                    "Hobbies:\n$pasatiempos \n"
                } else {
                    Toast.makeText(this, "Las Cotraseñas no son iguales !!!", Toast.LENGTH_SHORT).show();
                    tv_resultado.text = getString(R.string.error_contrasena)
                }
            }
        }
    }
    /////////////////////funcion de vacios//////////////////////
    fun vacios(nombre :String, cedula : String,
               correo : String, telefono :String ,contrasena : String,
               rcontrasena: String, fecha_nacimiento: String): Boolean {
        if (nombre.isNullOrEmpty()){
            Toast.makeText(this, "Escribir Nombre !!!", Toast.LENGTH_SHORT).show();
            // Focus en jugar y abrir el Teclado
            et_nombre.requestFocus();
        }else if (cedula.isNullOrEmpty()) {
            Toast.makeText(this, "Escribir Cedula !!!", Toast.LENGTH_SHORT).show();
            // Focus en correo y abrir el Teclado
            et_cedula.requestFocus();
        }else if (telefono.isNullOrEmpty()){
            Toast.makeText(this, "Escribir Telefono !!!", Toast.LENGTH_SHORT).show();
            // Focus en correo y abrir el Teclado
            et_telefono.requestFocus();
        }else if (correo.isNullOrEmpty()){
            Toast.makeText(this, "Escribir Correo !!!", Toast.LENGTH_SHORT).show();
            // Focus en telefono y abrir el Teclado
            et_correo.requestFocus();
        }else if (contrasena.isNullOrEmpty()){
            Toast.makeText(this, "Escribir Contrasena !!!", Toast.LENGTH_SHORT).show();
            // Focus en contrasena y abrir el Teclado
            et_contrasena.requestFocus();
        }else if (rcontrasena.isNullOrEmpty()){
            Toast.makeText(this, "Repetir Contrasena !!!", Toast.LENGTH_SHORT).show();
            // Focus en contrasena y abrir el Teclado
            et_Rcontrasena.requestFocus();
        }else if (fecha_nacimiento.isNullOrEmpty()){
            Toast.makeText(this, "Escribir  Fecha de Nacimiento !!!", Toast.LENGTH_SHORT).show();
            // Focus en fecha y abrir el Teclado
            tv_fecha_nacimiento.requestFocus()
        }else return true
        return false
    }
}
