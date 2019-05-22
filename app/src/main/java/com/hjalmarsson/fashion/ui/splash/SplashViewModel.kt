package com.hjalmarsson.fashion.ui.splash

import androidx.lifecycle.ViewModel;
import com.hjalmarsson.fashion.dagger.RepositoryComponent
import com.hjalmarsson.fashion.util.applyDefaultSchedulers
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SplashViewModel : ViewModel() {

    init {
        RepositoryComponent.inject(this)
    }

    fun loadData(): Observable<Long> {
        return Observable.timer(3, TimeUnit.SECONDS).applyDefaultSchedulers()
    }
}
