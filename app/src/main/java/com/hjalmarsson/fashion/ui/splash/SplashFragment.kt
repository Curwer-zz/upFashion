package com.hjalmarsson.fashion.ui.splash

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.hjalmarsson.fashion.R
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class SplashFragment : Fragment() {

    private lateinit var vm: SplashViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.splash_fragment, container, false)
        vm = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.loadData().subscribeBy(
            onComplete = {
                view?.findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToPopularFragment())
            },
            onError = {
                Timber.e(it)
            }
        )
    }
}
