package com.example.appremedio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.appremedio.db.AppDataBase
import com.example.appremedio.db.InstanceDb
import com.example.appremedio.db.entidades.Pessoa
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appremedio.databinding.FragmentNovoRemedioViewBinding
import com.example.appremedio.db.entidades.Remedio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NovoRemedioViewFragment : Fragment() {

    private var _binding: FragmentNovoRemedioViewBinding? = null
    private lateinit var db: AppDataBase

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).binding.fab.hide()
        db = InstanceDb.INSTANCE
        _binding = FragmentNovoRemedioViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).binding.toolbar.menu[0].isVisible = false
        val cadastrarButton = view.findViewById<Button>(R.id.button)
        cadastrarButton.setOnClickListener {
            val nome = view.findViewById<EditText>(R.id.editTextNome).text.toString()
            ///Transforma uma strin em date e salva em long no room
            val tipoRemedio = view.findViewById<EditText>(R.id.editTextTipoRemedio).text.toString()
            lifecycleScope.launch (Dispatchers.IO) {
                db.RemedioDao().inserirRemedio(Remedio(nome = nome, tipo = tipoRemedio))
            }
            val navController = findNavController()
            navController.navigate(R.id.ConsumoViewFragment)
        }
        //

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}