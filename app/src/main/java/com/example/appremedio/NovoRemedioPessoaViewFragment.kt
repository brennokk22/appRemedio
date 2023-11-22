package com.example.appremedio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.example.appremedio.databinding.FragmentNovoRemedioPessoaViewBinding
import com.example.appremedio.db.AppDataBase
import com.example.appremedio.db.InstanceDb
import com.example.appremedio.db.entidades.Consumo
import com.example.appremedio.db.entidades.PessoaRemedio
import com.example.appremedio.db.entidades.Remedio
import com.example.appremedio.db.entidades.TipoIntervalo
import com.example.appremedio.utils.ConfigElements
import com.example.appremedio.utils.Converters
import com.example.appremedio.utils.TextEditMaskUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class NovoRemedioPessoaViewFragment : Fragment() {

    private var _binding: FragmentNovoRemedioPessoaViewBinding? = null
    private lateinit var db: AppDataBase
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var editTextDataHoraInicio: EditText

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Aplicando maskara de texto
        (requireActivity() as MainActivity).binding.fab.hide()
        (requireActivity() as MainActivity).binding.toolbar.menu[0].isVisible = false
        db = InstanceDb.INSTANCE
        _binding = FragmentNovoRemedioPessoaViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Mascara de texto

        editTextDataHoraInicio = view.findViewById<EditText>(R.id.editTextDataHoraInicioW)
        editTextDataHoraInicio.addTextChangedListener(
            TextEditMaskUtil.mask(
                editTextDataHoraInicio,
                TextEditMaskUtil.FORMAT_DATE_HOUR
            )
        )
        //Load Spinner
        CoroutineScope(Dispatchers.IO).launch {
            val remedios = db.RemedioDao().buscarRemedios()
            val tiposIntervalos = db.TipoIntervaloDao().buscarTiposIntervalos()
            spinner = view.findViewById(R.id.spinner2)
            spinner2 = view.findViewById(R.id.spinner4)
            ConfigElements.setupSpinner(requireContext(), spinner, remedios)
            ConfigElements.setupSpinner(requireContext(), spinner2, tiposIntervalos)
        }
        val cadastrarButton = view.findViewById<Button>(R.id.button3)

        cadastrarButton.setOnClickListener {
            if (editTextDataHoraInicio.text.toString().matches("^\\d{2}\\/\\d{2}\\/\\d{4} - \\d{2}:\\d{2}\$".toRegex())){
                val remedio: Remedio = spinner.selectedItem as Remedio
                val tipoIntervalo: TipoIntervalo = spinner2.selectedItem as TipoIntervalo
                ///Transforma uma strin em date e salva em long no room
                val intervalo : Long =
                    view.findViewById<EditText>(R.id.editTextNumber).text.toString().toLong()
                val dataInicio = Converters.stringDateToLong(editTextDataHoraInicio.text.toString())
                runBlocking(Dispatchers.IO) {
                    if (dataInicio != null) {
                        db.PessoaRemedioDao().inserirPessoaRemedio(
                            PessoaRemedio(
                                idPessoa = 1,
                                idRemedio = remedio.id,
                                descricao = "",
                                estoque = "0",
                                idTipoIntervalo = tipoIntervalo.id
                            )
                        )
                        val idGerado: Long = db.PessoaRemedioDao().buscarMaxId()
                        var contadorTempo: Long = 0

                        val dataCalculada: Long = dataInicio
                        while (contadorTempo < 259200000) {
                            contadorTempo += (tipoIntervalo.constanteTempo * intervalo)
                            db.ConsumoDao().inserirConsumo(
                                Consumo(
                                    idPessoaRemedio = idGerado,
                                    datetimeAgendado = dataCalculada + contadorTempo,
                                    datetimeConsumo = null
                                )
                            )
                        }
                    }
                }
                val navController = findNavController()
                navController.navigate(R.id.ConsumoViewFragment)
            } else {
                val menDataInvalida = view.findViewById<TextView>(R.id.textView5)
                menDataInvalida.isVisible = true;
                CoroutineScope(Dispatchers.IO).launch{
                    delay(3000)
                    withContext(Dispatchers.Main){
                        menDataInvalida.isVisible = false
                    }
                }
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}