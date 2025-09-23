package ovh.gabrielhuav.a7cv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ovh.gabrielhuav.a7cv2.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        updateWelcomeMessage()
    }

    private fun setupClickListeners() {
        binding.btnNavigateFromFragment.setOnClickListener {
            navigateToSecondActivity()
        }

        binding.btnShowInfo.setOnClickListener {
            showFragmentInfo()
        }
    }

    private fun updateWelcomeMessage() {
        // Obtener el tema actual y mostrar mensaje personalizado
        val mainActivity = activity as? MainActivity
        val currentTheme = mainActivity?.getCurrentTheme() ?: "default"

        val themeMessage = when (currentTheme) {
            "light" -> "¡Disfrutando del tema claro! ☀️"
            "dark" -> "¡Explorando en modo oscuro! 🌙"
            else -> "¡Siguiendo el tema del sistema! 🌓"
        }

        binding.tvThemeInfo.text = themeMessage
    }

    private fun navigateToSecondActivity() {
        // Llamar función de MainActivity para navegar
        val mainActivity = activity as? MainActivity
        mainActivity?.navigateToSecondActivity(
            nombreUsuario = "Usuario desde WelcomeFragment",
            contador = 42,
            esVip = true
        )
    }

    private fun showFragmentInfo() {
        binding.tvFragmentInfo.text = """
            📱 ¡Hola desde WelcomeFragment!
            
            ✨ Este fragment está integrado dentro de MainActivity
            🎨 Se adapta automáticamente al tema seleccionado
            🔄 Puedes navegar desde aquí a SecondActivity
            🎯 Los temas se mantienen consistentes en toda la app
            
            ¡Prueba cambiar el tema y observa los cambios!
        """.trimIndent()

        binding.tvFragmentInfo.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        // Actualizar mensaje de tema cuando el fragment vuelve a ser visible
        updateWelcomeMessage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}