package com.example.my_architecture_templates.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyModel::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun myModelDao(): MyModelDao
}