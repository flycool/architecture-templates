package com.example.my_architecture_templates.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class MyModel(
    val name: String,
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}


@Dao
interface MyModelDao {

    @Query("select * from MyModel order by uid desc limit 10")
    fun getMyModels(): Flow<List<MyModel>>

    @Insert
    suspend fun insertMyModel(item: MyModel)
}