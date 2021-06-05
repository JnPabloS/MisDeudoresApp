package com.cubidevs.misdeudoresapp.ui.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cubidevs.misdeudoresapp.MisDeudoresApp
import com.cubidevs.misdeudoresapp.R
import com.cubidevs.misdeudoresapp.data.local.dao.DeudorDAO
import com.cubidevs.misdeudoresapp.data.local.entities.Deudor
import com.cubidevs.misdeudoresapp.databinding.FragmentCrearBinding
import com.cubidevs.misdeudoresapp.databinding.FragmentListaBinding

class ListaFragment : Fragment() {

    var allDeudores: List<Deudor> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_lista, container, false)

        val rv_deudores = root.findViewById<RecyclerView>(R.id.rv_deudores)

        rv_deudores.layoutManager = LinearLayoutManager(
            requireActivity().applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        rv_deudores.setHasFixedSize(true)

        var deudorDao: DeudorDAO = MisDeudoresApp.database.DeudorDAO()
        allDeudores = deudorDao.getDeudores()

        var deudoresRVAdapter = DeudoresRVAdapter(
            requireActivity().applicationContext,
            allDeudores as ArrayList<Deudor>
        )

        rv_deudores.adapter = deudoresRVAdapter

        deudoresRVAdapter.notifyDataSetChanged()

        return root
    }
}