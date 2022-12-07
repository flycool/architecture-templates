package com.example.my_architecture_templates.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.my_architecture_templates.data.local.database.AppDatabase
import com.example.my_architecture_templates.data.local.database.MyModelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideMyModelDao(appDatabase: AppDatabase): MyModelDao =
        appDatabase.myModelDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "MyModel"
        ).build()
    }

}