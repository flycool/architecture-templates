package com.example.my_architecture_templates.data

import com.example.my_architecture_templates.data.local.database.MyModel
import com.example.my_architecture_templates.data.local.database.MyModelDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MyModelRepository {
    val myModels: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultMyModelRepository @Inject constructor(
    private val myModelDao: MyModelDao,
) : MyModelRepository {
    override val myModels: Flow<List<String>> =
        myModelDao.getMyModels().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        myModelDao.insertMyModel(MyModel(name))
    }

}