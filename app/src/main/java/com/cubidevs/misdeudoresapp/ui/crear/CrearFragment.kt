package com.cubidevs.misdeudoresapp.ui.crear

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cubidevs.misdeudoresapp.MisDeudoresApp
import com.cubidevs.misdeudoresapp.data.local.dao.DeudorDAO
import com.cubidevs.misdeudoresapp.data.local.entities.Deudor
import com.cubidevs.misdeudoresapp.databinding.FragmentCrearBinding
import java.sql.Types.NULL

class CrearFragment : Fragment() {

    private lateinit var binding: FragmentCrearBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCrearBinding.inflate(inflater, container, false)

        with(binding){
            guardarButton.setOnClickListener(){
                val nombre = nombreEditText.text.toString()
                val telefono = telefonoEditText.text.toString()
                val valor = valorEditText.text.toString().toLong()

                guardarDeudorEnLocal(nombre, telefono, valor)
            }

            return binding.root
        }
    }

    private fun guardarDeudorEnLocal(nombre: String?, telefono: String?, valor: Long?) {
        val deudor = Deudor ( id = NULL, nombre = nombre, telefono = telefono, valor = valor)
        val deudorDAO : DeudorDAO = MisDeudoresApp.database.DeudorDAO()
        deudorDAO.insertDeudor(deudor)
        cleanViews()
    }

    fun cleanViews() {
        binding.nombreEditText.setText("")
        binding.telefonoEditText.setText("")
        binding.valorEditText.setText("")
    }
}