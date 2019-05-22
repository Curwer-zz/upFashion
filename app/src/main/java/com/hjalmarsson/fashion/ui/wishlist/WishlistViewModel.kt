package com.hjalmarsson.fashion.ui.wishlist

import androidx.lifecycle.ViewModel;
import com.hjalmarsson.fashion.dagger.RepositoryComponent

class WishlistViewModel : ViewModel() {

    init {
        RepositoryComponent.inject(this)
    }
}
