package com.example.appremedio


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appremedio.databinding.FragmentConsumoViewBinding
import com.example.appremedio.db.AppDataBase
import com.example.appremedio.db.InstanceDb
import com.example.appremedio.manager.RecycleViewManager
import com.example.appremedio.utils.ConfigElements
import com.example.appremedio.utils.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ConsumoViewFragment : Fragment() {

    private var _binding: FragmentConsumoViewBinding? = null
    lateinit var db: AppDataBase
    private lateinit var spinner: Spinner
    private lateinit var recycler: RecyclerView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity).binding.fab.show()
        db = InstanceDb.INSTANCE
        _binding = FragmentConsumoViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = view.findViewById(R.id.spinner)
        recycler = view.findViewById(R.id.recyclerView)
        CoroutineScope(Dispatchers.IO).launch {
            val remedios = db.RemedioDao().buscarRemedios()
            val consumos = db.ConsumoDao().buscarConsumoOrdernado()
            val listConsumo: ArrayList<String> = java.util.ArrayList()
            for (item in consumos) {
                val idRemedio =
                    db.PessoaRemedioDao().buscarPessoaRemediosId(item.idPessoaRemedio)[0].idRemedio
                val nomeRemedio =
                    db.RemedioDao().buscaRemedioId(idRemedio)[0].nome
                val dataString = Converters.longToStringDate(item.datetimeAgendado)
                listConsumo.add("$nomeRemedio - $dataString")
            }
            withContext(Dispatchers.Main) {
                ConfigElements.setupSpinner(requireContext(), spinner, remedios)
                ConfigElements.setupRecycler(requireContext(), recycler, listConsumo)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}