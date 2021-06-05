package com.cubidevs.misdeudoresapp.ui.actualizar

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
import com.cubidevs.misdeudoresapp.databinding.FragmentActualizarBinding

class ActualizarFragment : Fragment() {

    private lateinit var binding: FragmentActualizarBinding
    private var isSearching = true
    private  var idDeudor  : String = ""
    private var EMPTY = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActualizarBinding.inflate(inflater, container, false)

        binding.modificarButton.setOnClickListener {
            val nombre = binding.nombreBuscarEditText.text.toString()
            val telefono = binding.telefonoEditText.text.toString()
            val valor = binding.valorEditText.text.toString()

            updateDeudor(nombre, telefono, valor)

            binding.nombreBuscarEditText.doAfterTextChanged {
                binding.telefonoEditText.setText(EMPTY)
                binding.valorEditText.setText(EMPTY)
                isSearching = true
                binding.modificarButton.text = "buscar"
            }
        }

        return binding.root
    }

    private fun updateDeudor(nombre: String, telefono: String, valor: String) {
        val deudorDAO : DeudorDAO = MisDeudoresApp.database.DeudorDAO()

        if(isSearching) {
            val deudor: Deudor = deudorDAO.searchDeudor(nombre)
            if (deudor != null) {
                isSearching = false
                binding.modificarButton.text = "actualizar"
                idDeudor = deudor.id.toString()
                binding.telefonoEditText.setText(deudor.telefono)
                binding.valorEditText.setText(deudor.valor.toString())
            } else {
                Toast.makeText(context, "No existe", Toast.LENGTH_SHORT).show()
            }
        }else{
            val deudor = Deudor(id = idDeudor.toInt(), nombre = nombre, telefono = telefono, valor = valor.toLong())
            deudorDAO.updateDeudor(deudor)
            isSearching = true
            binding.modificarButton.text = "buscar"
            Toast.makeText(context, "Deudor actualizado", Toast.LENGTH_SHORT).show()
        }
    }
}