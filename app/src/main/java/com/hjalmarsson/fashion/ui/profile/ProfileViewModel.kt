package com.hjalmarsson.fashion.ui.profile

import androidx.lifecycle.ViewModel;
import com.hjalmarsson.fashion.dagger.RepositoryComponent

class ProfileViewModel : ViewModel() {

    init {
        RepositoryComponent.inject(this)
    }
}
