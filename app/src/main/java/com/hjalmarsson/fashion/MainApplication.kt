package com.hjalmarsson.fashion

import android.app.Application
import com.hjalmarsson.fashion.dagger.*
import timber.log.Timber.DebugTree
import timber.log.Timber

class MainApplication : Application() {
    lateinit var appModule: AppModule
    lateinit var dataSourceModule: DataSourceModule
    lateinit var repositoryModule: RepositoryModule

    override fun onCreate() {
        appComponent.inject(this)

        super.onCreate()

        appModule = AppModule(this)
        dataSourceModule = DataSourceModule()
        repositoryModule = RepositoryModule()

        @Suppress("DEPRECATION")
        repositoryComponent = DaggerRepositoryComponent.builder()
            .appModule(appModule)
            .repositoryModule(repositoryModule)
            .dataSourceModule(dataSourceModule)
            .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    companion object {
        val appComponent: AppComponent by lazy { DaggerAppComponent.create() }
        lateinit var repositoryComponent: RepositoryComponent
            private set
    }
}
