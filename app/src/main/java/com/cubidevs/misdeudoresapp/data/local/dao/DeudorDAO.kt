package com.cubidevs.misdeudoresapp.data.local.dao

import androidx.room.*
import com.cubidevs.misdeudoresapp.data.local.entities.Deudor

@Dao
interface DeudorDAO {
    @Insert
    fun insertDeudor(deudor: Deudor)

    @Query("SELECT * FROM tabla_deudor WHERE nombre LIKE :nombre")
    fun searchDeudor(nombre: String): Deudor

    @Delete
    fun deleteDeudor(deudor: Deudor)

    @Update
    fun updateDeudor(deudor: Deudor)

    @Query("SELECT * FROM tabla_deudor")
    fun getDeudores(): List<Deudor>
}