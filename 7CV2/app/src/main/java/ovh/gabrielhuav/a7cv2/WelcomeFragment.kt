package ovh.gabrielhuav.a7cv2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class WelcomeFragment : Fragment() {

    // Variables para acceder a las vistas
    private lateinit var etNombre: EditText
    private lateinit var btnSaludar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var tvResultado: TextView
    private lateinit var btnOpenSecond: Button
    private lateinit var btnOpenActivity: Button

    // Se ejecuta para crear la vista del fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    // Se ejecuta después de que la vista se ha creado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar las vistas usando findViewById
        initViews(view)

        // Configurar los listeners
        setupClickListeners()
    }

    private fun initViews(view: View) {
        etNombre = view.findViewById(R.id.etNombre)
        btnSaludar = view.findViewById(R.id.btnSaludar)
        btnLimpiar = view.findViewById(R.id.btnLimpiar)
        btnOpenSecond = view.findViewById(R.id.btnOpenSecond)
        tvResultado = view.findViewById(R.id.tvResultado)
        btnOpenActivity = view.findViewById(R.id.btnOpenActivity)
    }

    private fun setupClickListeners() {
        // Listener para el botón saludar
        btnSaludar.setOnClickListener {
            mostrarSaludo()
        }

        // Listener para el botón limpiar
        btnLimpiar.setOnClickListener {
            limpiarResultado()
        }

        // Listener para abrir el segundo fragment
        btnOpenSecond.setOnClickListener {
            openSecondFragment()
        }

        btnOpenActivity.setOnClickListener {
            openSecondActivity()
        }
    }

    private fun mostrarSaludo() {
        // Obtener el texto del EditText
        val nombre = etNombre.text.toString().trim()

        // Validar que no esté vacío
        if (nombre.isNotEmpty()) {
            val saludo = "¡Hola $nombre! Bienvenido a tu primera app con Fragments 😊"
            tvResultado.text = saludo
            tvResultado.visibility = View.VISIBLE
        } else {
            tvResultado.text = "Por favor, ingresa tu nombre"
            tvResultado.visibility = View.VISIBLE
        }
    }

    private fun limpiarResultado() {
        etNombre.text.clear()
        tvResultado.text = ""
        tvResultado.visibility = View.GONE
    }

    private fun openSecondFragment() {
        // Log para verificar que se está llamando
        println("🚀 openSecondFragment() llamado!")

        // Crear instancia del segundo fragment
        val secondFragment = SecondFragment()

        // Usar add() en lugar de replace() para mantener el fragment actual
        parentFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, secondFragment)
            .addToBackStack("SecondFragment") // Para poder regresar con el botón atrás
            .commit()

        println("✅ Fragment transaction committed!")
    }

    // Método para abrir la segunda Activity
    private fun openSecondActivity() {
        // Obtener datos para enviar
        val nombreIngresado = etNombre.text.toString().trim()

        // Crear el Intent
        val intent = Intent(requireContext(), SecondActivity::class.java).apply {
            // Enviar datos a la nueva Activity
            putExtra("NOMBRE_USUARIO", nombreIngresado)
            putExtra("CONTADOR", 42) // Ejemplo de número
            putExtra("ES_VIP", true) // Ejemplo de boolean

            // También puedes enviar arrays, objetos serializables, etc.
            putExtra("DATOS_EXTRA", "Información adicional desde WelcomeFragment")
        }

        // Iniciar la Activity
        startActivity(intent)

        // Opcional: Agregar animación de transición
        requireActivity().overridePendingTransition(
            android.R.anim.slide_in_left,  // Animación de entrada
            android.R.anim.slide_out_right // Animación de salida
        )
    }
}