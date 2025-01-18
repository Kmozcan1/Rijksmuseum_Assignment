package com.albertheijn.rijksmuseumassignment.application.di

import com.albertheijn.rijksmuseumassignment.data.repository.RijksmuseumRepositoryImpl
import com.albertheijn.rijksmuseumassignment.domain.repository.RijksmuseumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRijksmuseumRepository(
        impl: RijksmuseumRepositoryImpl
    ): RijksmuseumRepository
}