package ovh.gabrielhuav.a7cv2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ovh.gabrielhuav.a7cv2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar tema antes de super.onCreate()
        applyStoredTheme()

        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupClickListeners()

        // Cargar fragment por defecto si no hay uno previo
        if (savedInstanceState == null) {
            loadSecondFragment()
        }

        // Recibir datos del Activity anterior
        receiveDataFromPreviousActivity()
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

    private fun setupViews() {
        // Configurar la toolbar
        supportActionBar?.apply {
            title = "Segunda Activity"
            setDisplayHomeAsUpEnabled(true)
        }

        // Actualizar indicador de tema
        updateThemeStatusText()
    }

    private fun setupClickListeners() {
        binding.btnBackToMain.setOnClickListener {
            goBackToMainActivity()
        }

        binding.btnFinishActivity.setOnClickListener {
            finish()
        }

        binding.btnToggleTheme.setOnClickListener {
            toggleTheme()
        }
    }

    private fun loadSecondFragment() {
        val secondFragment = SecondFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, secondFragment)
            .commit()
    }

    private fun receiveDataFromPreviousActivity() {
        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO")
        val numeroContador = intent.getIntExtra("CONTADOR", 0)
        val esVip = intent.getBooleanExtra("ES_VIP", false)
        val origen = intent.getStringExtra("ORIGEN")

        binding.tvDataReceived.text = """
            📦 Datos recibidos:
            👤 Nombre: ${nombreUsuario ?: "No especificado"}
            🔢 Contador: $numeroContador
            ⭐ Es VIP: $esVip
            📍 Origen: ${origen ?: "Desconocido"}
        """.trimIndent()
    }

    private fun toggleTheme() {
        val sharedPref = getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)
        val currentTheme = sharedPref.getString("selected_theme", "default")

        val newTheme = when (currentTheme) {
            "light" -> "dark"
            "dark" -> "default"
            else -> "light"
        }

        with(sharedPref.edit()) {
            putString("selected_theme", newTheme)
            apply()
        }

        applyStoredTheme()
        updateThemeStatusText()
        recreate()
    }

    private fun updateThemeStatusText() {
        val sharedPref = getSharedPreferences("theme_preferences", Context.MODE_PRIVATE)
        val currentTheme = sharedPref.getString("selected_theme", "default")

        val statusText = when (currentTheme) {
            "light" -> "Tema actual: Claro ☀️"
            "dark" -> "Tema actual: Oscuro 🌙"
            else -> "Tema actual: Sistema 🌓"
        }
        binding.tvThemeStatus.text = statusText

        val buttonText = when (currentTheme) {
            "light" -> "🌙 Cambiar a Oscuro"
            "dark" -> "🌓 Seguir Sistema"
            else -> "☀️ Cambiar a Claro"
        }
        binding.btnToggleTheme.text = buttonText
    }

    private fun goBackToMainActivity() {
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}