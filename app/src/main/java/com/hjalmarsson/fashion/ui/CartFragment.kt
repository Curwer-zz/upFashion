package com.hjalmarsson.fashion.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hjalmarsson.fashion.R

class CartFragment : Fragment() {

    private lateinit var vm: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cart_fragment, container, false)
        vm = ViewModelProviders.of(this).get(CartViewModel::class.java)

        return view
    }
}
