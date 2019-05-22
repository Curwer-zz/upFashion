package com.hjalmarsson.fashion.dagger

import com.hjalmarsson.fashion.MainApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(app: MainApplication)
}
