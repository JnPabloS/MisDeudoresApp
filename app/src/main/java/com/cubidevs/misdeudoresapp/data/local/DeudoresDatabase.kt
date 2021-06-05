package com.cubidevs.misdeudoresapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cubidevs.misdeudoresapp.data.local.dao.DeudorDAO
import com.cubidevs.misdeudoresapp.data.local.entities.Deudor

@Database(entities = [Deudor::class], version = 1)

abstract class DeudoresDatabase : RoomDatabase() {

    abstract fun DeudorDAO():DeudorDAO
}