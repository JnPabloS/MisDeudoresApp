package com.cubidevs.misdeudoresapp.ui.buscar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.cubidevs.misdeudoresapp.MisDeudoresApp
import com.cubidevs.misdeudoresapp.data.local.dao.DeudorDAO
import com.cubidevs.misdeudoresapp.data.local.entities.Deudor
import com.cubidevs.misdeudoresapp.databinding.FragmentBuscarBinding

class BuscarFragment : Fragment() {

    private lateinit var binding: FragmentBuscarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuscarBinding.inflate(inflater, container, false)

        binding.buscarButton.setOnClickListener {
            val nombre = binding.nombreBuscarEditText.text.toString()
            buscarDeudor(nombre)
        }

        binding.nombreBuscarEditText.doAfterTextChanged {

            binding.telefonoTextView.text = "Teléfono: "
            binding.valorTextView.text = "Valor: "
        }
        return binding.root
    }

    private fun buscarDeudor(nombre: String) {
        val deudorDAO : DeudorDAO = MisDeudoresApp.database.DeudorDAO()
        val deudor: Deudor = deudorDAO.searchDeudor(nombre)

        if(deudor != null){
            binding.telefonoTextView.text = "Teléfono: "+deudor.telefono.toString()
            binding.valorTextView.text = "Valor: "+deudor.valor.toString()
        }else{
            Toast.makeText(context, "No existe", Toast.LENGTH_SHORT).show()
        }

    }
}