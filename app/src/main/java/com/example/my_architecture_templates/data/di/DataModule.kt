package com.example.my_architecture_templates.data.di

import com.example.my_architecture_templates.data.DefaultMyModelRepository
import com.example.my_architecture_templates.data.MyModelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsMyModelRepository(modelRepository: DefaultMyModelRepository): MyModelRepository

}

class FakeMyModelRepository @Inject constructor() : MyModelRepository {
    override val myModels: Flow<List<String>> = flowOf(fakeModels)

    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeModels = listOf("one", "two", "three")