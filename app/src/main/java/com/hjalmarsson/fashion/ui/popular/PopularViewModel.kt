package com.hjalmarsson.fashion.ui.popular

import androidx.lifecycle.ViewModel
import com.hjalmarsson.fashion.dagger.RepositoryComponent

class PopularViewModel : ViewModel() {


    init {
        RepositoryComponent.inject(this)
    }

    fun getData() {

    }

}
