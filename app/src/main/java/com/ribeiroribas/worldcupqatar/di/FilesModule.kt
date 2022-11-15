package com.ribeiroribas.worldcupqatar.di

import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.ribeiroribas.worldcupqatar.dao.FilesDaoImpl
import com.ribeiroribas.worldcupqatar.repository.WorldCupDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilesModule {

    @Singleton
    @Provides
    fun providesFilesDaoImpl(
        @ApplicationContext appContext: Context,
        mapper: ObjectMapper
    ): FilesDaoImpl = FilesDaoImpl(
        appContext = appContext,
        mapper = mapper
    )

    @Singleton
    @Provides
    fun providesWorldCupDataRepository(dao: FilesDaoImpl): WorldCupDataRepository =
        WorldCupDataRepository(dao)
}