package mx.edu.unpa.miandroid

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Dashboard : AppCompatActivity() {
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        prefs = getSharedPreferences("usuario", MODE_PRIVATE)
        //val usuario= prefs.getString("usuario", null)

        //val usuario= intent.getStringExtra("usuario")
        //val contrasena= intent.getStringExtra("contrasena")
        //Toast.makeText(this, "Bienvenido: " + usuario, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        val usuario = prefs.getString("usuario", null)
        Toast.makeText(this, "Bienvenido" + usuario, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_main, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //return super.onOptionsItemSelected(item)
        return when (item.itemId){
             R.id.action_config ->{
                Toast.makeText(this,"Configuración", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_ ->{
                //Toast.makeText(this,"Cerrar sesión", Toast.LENGTH_LONG).show()
                prefs = getSharedPreferences("sesion", MODE_PRIVATE)
                prefs.edit().clear().apply()

                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}