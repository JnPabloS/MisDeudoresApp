package com.cubidevs.misdeudoresapp.ui.borrar

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cubidevs.misdeudoresapp.MisDeudoresApp
import com.cubidevs.misdeudoresapp.R
import com.cubidevs.misdeudoresapp.data.local.dao.DeudorDAO
import com.cubidevs.misdeudoresapp.data.local.entities.Deudor
import com.cubidevs.misdeudoresapp.databinding.FragmentBorrarBinding
import kotlinx.coroutines.NonCancellable.cancel

class BorrarFragment : Fragment() {

    private lateinit var binding: FragmentBorrarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBorrarBinding.inflate(inflater, container, false)

        binding.borrarButton.setOnClickListener {
            val nombre = binding.nombreBorrarEditText.text.toString()
            borrarDeudor(nombre)
        }

        return binding.root
    }

    private fun borrarDeudor(nombre: String) {
        val deudorDAO : DeudorDAO = MisDeudoresApp.database.DeudorDAO()
        val deudor: Deudor = deudorDAO.searchDeudor(nombre)

        if(deudor != null){
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setMessage("Desea eliminar a " + deudor.nombre + " su deuda es: "+ deudor.valor + "?")
                    setPositiveButton(
                        R.string.accept
                    ) { dialog, id ->
                        deudorDAO.deleteDeudor(deudor)
                        Toast.makeText(context, "Deudor borrado", Toast.LENGTH_SHORT).show()
                    }
                    setNegativeButton(
                        R.string.cancel
                    ) { dialog, id ->
                    }
                }
                builder.create()
            }
            alertDialog?.show()
        }else{
            Toast.makeText(context, "No existe", Toast.LENGTH_SHORT).show()
        }
    }

}