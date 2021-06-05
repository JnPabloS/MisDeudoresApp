package com.cubidevs.misdeudoresapp.ui.lista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cubidevs.misdeudoresapp.R
import com.cubidevs.misdeudoresapp.data.local.entities.Deudor
import com.cubidevs.misdeudoresapp.databinding.ItemDeudorBinding

class DeudoresRVAdapter (
    var context: Context,
    var deudoresList: ArrayList<Deudor>
) : RecyclerView.Adapter<DeudoresRVAdapter.DeudoresViewHolder>()  {

    //private lateinit var binding : ItemDeudorBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeudoresViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_deudor, parent,false)

        //binding = ItemDeudorBinding.inflate(LayoutInflater.from(context), parent, false)

        return DeudoresViewHolder(itemView, context)
    }

    override fun getItemCount(): Int = deudoresList.size

    override fun onBindViewHolder(
        holder: DeudoresViewHolder,
        position: Int
    ) {
        val deudor: Deudor = deudoresList[position]
        holder.bindDeudor(deudor) //, binding)
    }

    class DeudoresViewHolder(
        itemView: View,
        context: Context
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindDeudor(deudor: Deudor) //, bind: ItemDeudorBinding)
        {
            itemView.tvNombre.text = deudor.nombre
            itemView.tvDeuda.text = deudor.valor.toString()
        }

    }
}