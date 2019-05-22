package com.hjalmarsson.fashion.dagger

import com.hjalmarsson.fashion.repository.LoadingRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesLoadingRepository() = LoadingRepository()
}
