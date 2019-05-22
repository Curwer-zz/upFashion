package com.hjalmarsson.fashion.dagger

import com.hjalmarsson.fashion.MainApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (DataSourceModule::class)])
interface DataSourceComponent {
    companion object : DataSourceComponent by MainApplication.repositoryComponent
}
