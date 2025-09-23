package ovh.gabrielhuav.a7cv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ovh.gabrielhuav.a7cv2.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        showFragmentDetails()
    }

    private fun setupClickListeners() {
        binding.btnActionOne.setOnClickListener {
            performActionOne()
        }

        binding.btnActionTwo.setOnClickListener {
            performActionTwo()
        }

        binding.btnShowStats.setOnClickListener {
            showStatistics()
        }
    }

    private fun showFragmentDetails() {
        binding.tvFragmentDescription.text = """
            🎯 SecondFragment
            
            Este fragment se muestra dentro de SecondActivity y demuestra:
            • Navegación anidada entre Activities y Fragments
            • Consistencia de temas en toda la aplicación
            • Interacciones dentro del fragment
        """.trimIndent()
    }

    private fun performActionOne() {
        binding.tvActionResult.text = "✅ Acción 1 ejecutada correctamente"
        binding.tvActionResult.visibility = View.VISIBLE

        // Simular alguna funcionalidad
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.postDelayed({
            binding.progressBar.visibility = View.GONE
        }, 1500)
    }

    private fun performActionTwo() {
        binding.tvActionResult.text = "🔧 Acción 2 completada con éxito"
        binding.tvActionResult.visibility = View.VISIBLE

        // Cambiar el estado de algún elemento
        binding.switchOption.isChecked = !binding.switchOption.isChecked
    }

    private fun showStatistics() {
        val stats = """
            📊 Estadísticas del Fragment:
            
            🎨 Tema adaptable: ✅
            🔄 Navegación funcional: ✅
            📱 Responsive design: ✅
            ⚡ Rendimiento optimizado: ✅
            
            ¡Todo funcionando perfectamente!
        """.trimIndent()

        binding.tvStats.text = stats
        binding.tvStats.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}