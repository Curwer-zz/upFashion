package com.hjalmarsson.fashion.dagger

import com.hjalmarsson.fashion.ui.popular.PopularViewModel
import com.hjalmarsson.fashion.MainApplication
import com.hjalmarsson.fashion.MainViewModel
import com.hjalmarsson.fashion.ui.CartViewModel
import com.hjalmarsson.fashion.ui.profile.ProfileViewModel
import com.hjalmarsson.fashion.ui.splash.SplashViewModel
import com.hjalmarsson.fashion.ui.wishlist.WishlistViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (RepositoryModule::class), (DataSourceModule::class)])
interface RepositoryComponent : DataSourceComponent {
    companion object : RepositoryComponent by MainApplication.repositoryComponent

    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: PopularViewModel)
    fun inject(viewModel: SplashViewModel)
    fun inject(cartViewModel: CartViewModel)
    fun inject(profileViewModel: ProfileViewModel)
    fun inject(wishlistViewModel: WishlistViewModel)
}
