package ovh.gabrielhuav.a7cv2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ovh.gabrielhuav.a7cv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar tema antes de super.onCreate()
        applyStoredTheme()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Solo cargar el fragment si no existe uno previamente
        // (esto previene duplicados al rotar la pantalla)
        if (savedInstanceState == null) {
            loadWelcomeFragment()
        }

        // Configurar botones
        setupClickListeners()

        // Actualizar el indicador de tema
        updateThemeStatusText()
    }

    private fun applyStoredTheme() {
        val sharedPref = getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)
        val theme = sharedPref.getString("selected_theme", "default")

        when (theme) {
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "default" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
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

        // Configurar botón para cambiar tema
        binding.btnToggleTheme.setOnClickListener {
            toggleTheme()
        }
    }

    private fun toggleTheme() {
        val sharedPref = getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)
        val currentTheme = sharedPref.getString("selected_theme", "default")

        val newTheme = when (currentTheme) {
            "light" -> "dark"
            "dark" -> "default"
            else -> "light"
        }

        // Guardar nueva preferencia
        with(sharedPref.edit()) {
            putString("selected_theme", newTheme)
            apply()
        }

        // Aplicar nuevo tema
        applyStoredTheme()

        // Actualizar texto del botón y indicador antes de recrear
        updateThemeButtonText(newTheme)
        updateThemeStatusText()

        // Recrear activity para aplicar cambios
        recreate()
    }

    private fun updateThemeButtonText(theme: String) {
        val buttonText = when (theme) {
            "light" -> "☀️ Cambiar Tema"
            "dark" -> "🌙 Cambiar Tema"
            else -> "🌓 Cambiar Tema"
        }
        binding.btnToggleTheme.text = buttonText
    }

    private fun updateThemeStatusText() {
        val currentTheme = getCurrentTheme()
        val statusText = when (currentTheme) {
            "light" -> "Tema actual: Claro"
            "dark" -> "Tema actual: Oscuro"
            else -> "Tema actual: Sistema"
        }
        binding.tvThemeStatus.text = statusText
        updateThemeButtonText(currentTheme)
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

    // Función para obtener el tema actual
    fun getCurrentTheme(): String {
        val sharedPref = getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)
        return sharedPref.getString("selected_theme", "default") ?: "default"
    }
}