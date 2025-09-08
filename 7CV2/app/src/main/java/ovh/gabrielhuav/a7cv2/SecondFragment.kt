package ovh.gabrielhuav.a7cv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ovh.gabrielhuav.a7cv2.databinding.ActivityMainBinding

class SecondFragment : Fragment() {

    private lateinit var tvCounter: TextView
    private lateinit var btnIncrement: Button
    private lateinit var btnDecrement: Button
    private lateinit var btnClose: Button

    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupClickListeners()
        updateCounter()
    }

    private fun initViews(view: View) {
        tvCounter = view.findViewById(R.id.tvCounter)
        btnIncrement = view.findViewById(R.id.btnIncrement)
        btnDecrement = view.findViewById(R.id.btnDecrement)
        btnClose = view.findViewById(R.id.btnClose)
    }
    private lateinit var binding: ActivityMainBinding

    private fun setupClickListeners() {
        btnIncrement.setOnClickListener {
            counter++
            updateCounter()
        }

        btnDecrement.setOnClickListener {
            counter--
            updateCounter()
        }

        btnClose.setOnClickListener {
            closeFragment()
        }
    }

    private fun updateCounter() {
        tvCounter.text = "Contador: $counter"
    }

    private fun closeFragment() {
        // Remover este fragment y regresar al anterior
        parentFragmentManager.popBackStack()
    }
}