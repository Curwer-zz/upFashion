package com.hjalmarsson.fashion.ui.wishlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjalmarsson.fashion.R

class WishlistFragment : Fragment() {

    private lateinit var vm: WishlistViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.wishlist_fragment, container, false)
        vm = ViewModelProviders.of(this).get(WishlistViewModel::class.java)

        return view
    }
}
