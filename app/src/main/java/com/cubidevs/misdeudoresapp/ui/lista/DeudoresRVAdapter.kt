package com.cubidevs.misdeudoresapp.ui.lista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cubidevs.misdeudoresapp.R
import com.cubidevs.misdeudoresapp.data.local.entities.Deudor
import com.cubidevs.misdeudoresapp.databinding.ItemDeudorBinding

class DeudoresRVAdapter (
    var context: Context,
    var deudoresList: ArrayList<Deudor>
) : RecyclerView.Adapter<DeudoresRVAdapter.DeudoresViewHolder>()  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeudoresViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_deudor, parent,false)
        return DeudoresViewHolder(itemView, context)
    }

    override fun getItemCount(): Int = deudoresList.size

    override fun onBindViewHolder(
        holder: DeudoresViewHolder,
        position: Int
    ) {
        val deudor: Deudor = deudoresList[position]
        holder.bindDeudor(deudor)
    }

    class DeudoresViewHolder(
        itemView: View,
        context: Context
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemDeudorBinding.bind(itemView)

        fun bindDeudor(deudor: Deudor)
        {
            binding.tvNombre.text = deudor.nombre
            binding.tvDeuda.text = deudor.valor.toString()

            binding.tvNombre.setOnClickListener {
                Toast.makeText(itemView.getContext(),deudor.nombre,Toast.LENGTH_LONG).show()
            }
        }

    }
}