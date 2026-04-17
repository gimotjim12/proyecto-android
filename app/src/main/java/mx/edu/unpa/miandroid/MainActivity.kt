package mx.edu.unpa.miandroid

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.unpa.miandroid.client.RetrofitClient
import mx.edu.unpa.miandroid.model.Usuario
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = getSharedPreferences("usuario", MODE_PRIVATE)
        val usuario= prefs.getString("usuario", null)

        if(usuario != null){
            val intent = Intent (this, Dashboard::class.java)
            startActivity(intent)
        }
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Se inicializan las variables, se carga los elementos del XML (Caja de texto, botones, etc.)
        Log.d("MAIN_ACTIVITY", "Metodo onCreate")
    }

    override fun onStart(){
        super.onStart()
        //Se cargan los recursos visuales, el layout ya es visible, pero el user no puede interactuar con la pantalla
        Log.d("MAIN_ACTIVITY", "Metodo onStar")
    }

    override fun onResume(){
        super.onResume()
        //ya se cargo el Layout, el usuari puede interactuar con los elementos en pantalla
        Log.d("MAIN_ACTIVITY", "Metodo onResume")

        RetrofitClient.instance.getUsuario().enqueue(object : retrofit2.Callback<List<Usuario>>{
            override fun onResponse(
                call: Call<List<Usuario>?>,
                response: Response<List<Usuario>?>) {
                if (response.isSuccessful){
                    Log.d("RETROFIT", "Conexión exitosa!!")

                    val lista = response.body()
                    lista?.forEach {
                        Log.d("DB_ANDROID", it.nombre)
                    }
                }

            }

            override fun onFailure(call : Call<List<Usuario>?>?,  t: Throwable) {
                Log.e("RETROFIT", "Conexion exitosa")
                t.printStackTrace()
            }
        })
    }

    override fun onPause(){
        super.onPause()
        //El layout pierde foco, pero se puede ver en pantalla
        //Entra a un mensaje externo o notificacion push
        //Se abre otra actvidad (Alarma)
        Log.d("MAIN_ACTIVITY", "Metodo onPause")
    }

    override fun onStop(){
        super.onStop()
        //Se cambia a otra pantalla, y se minimiza el layout
        Log.d("MAIN_ACTIVITY", "Metodo onStop")
    }

    override fun onRestart(){
        super.onRestart()
        //Se ejecuta cuando se regresa el layout  (Pantalla(Actividad))
        Log.d("MAIN_ACTIVITY", "Metodo onRestart")
    }

    override fun onDestroy(){
        super.onDestroy()
        //Se destruye el layout
        Log.d("MAIN_ACTIVITY", "Metodo onDestroy")
    }

    fun saludar(view: View){
        val nombre = findViewById<EditText>(R.id.txtUsuario)
        val saludar = findViewById<Button>(R.id.btnLogin)

        //Toast.makeText(this, "Estas cachonchito $nombre", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Estas cachonchito" + nombre, Toast.LENGTH_LONG).show()
    }

    fun login(view : View){
        val usuario = findViewById<EditText>(R.id.txtUsuario)
        val contrasena = findViewById<EditText>(R.id.txtContrasenia)

        if(usuario.toString().isNotEmpty()){
            val editar = prefs.edit()
            editar.putString("usuario", usuario.toString())
            editar.putString("contraseña", contrasena.toString())
            editar.apply()

            val intent = Intent(this, Dashboard:: class.java)
            startActivity(intent)
        }

/*
        val intent = Intent(this, Dashboard::class.java)
        //intent.putExtra("nombre", "Gael Valerio ")
        intent.putExtra("usuario", usuario.getText().toString())
        intent.putExtra("contrasena", contrasena.getText().toString())
        startActivity(intent);*/
    }


}