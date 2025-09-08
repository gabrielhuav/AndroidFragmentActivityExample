package ovh.gabrielhuav.a7cv2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ovh.gabrielhuav.a7cv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Solo cargar el fragment si no existe uno previamente
        // (esto previene duplicados al rotar la pantalla)
        if (savedInstanceState == null) {
            loadWelcomeFragment()
        }

        // Configurar botones si los tienes en MainActivity
        setupClickListeners()
    }

    private fun loadWelcomeFragment() {
        // Crear una instancia del fragment
        val welcomeFragment = WelcomeFragment()

        // Usar el FragmentManager para cargar el fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, welcomeFragment)
            .commit()
    }

    private fun setupClickListeners() {
        binding.btnOpenSecondActivity.setOnClickListener {
            openSecondActivity()
        }
    }

    // Función para abrir SecondActivity desde MainActivity
    fun openSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            // Enviar datos desde MainActivity
            putExtra("NOMBRE_USUARIO", "Usuario desde MainActivity")
            putExtra("CONTADOR", 100)
            putExtra("ES_VIP", false)
            putExtra("ORIGEN", "MainActivity")
        }
        startActivity(intent)

        // Opcional: Agregar animación de transición
        overridePendingTransition(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
    }

    // Función que puede ser llamada desde los fragments
    fun navigateToSecondActivity(nombreUsuario: String, contador: Int, esVip: Boolean) {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("NOMBRE_USUARIO", nombreUsuario)
            putExtra("CONTADOR", contador)
            putExtra("ES_VIP", esVip)
            putExtra("ORIGEN", "WelcomeFragment")
        }
        startActivity(intent)
    }
}