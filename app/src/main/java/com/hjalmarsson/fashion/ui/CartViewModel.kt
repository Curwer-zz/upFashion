package com.hjalmarsson.fashion.ui

import androidx.lifecycle.ViewModel;
import com.hjalmarsson.fashion.dagger.RepositoryComponent

class CartViewModel : ViewModel() {

    init {
        RepositoryComponent.inject(this)
    }
}
