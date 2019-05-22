package com.hjalmarsson.fashion

import androidx.lifecycle.ViewModel
import com.hjalmarsson.fashion.dagger.RepositoryComponent

class MainViewModel: ViewModel() {
    var destinationId: Int? = 0
        private set

    val shouldShowNavigation = setOf(R.id.popularFragment, R.id.profileFragment, R.id.wishlistFragment, R.id.cartFragment)

    init {
        RepositoryComponent.inject(this)
    }

    fun updateDestinationId(id: Int) {
        destinationId = id
    }

    fun shouldShowNavigation(): Boolean = shouldShowNavigation.contains(destinationId)
}
