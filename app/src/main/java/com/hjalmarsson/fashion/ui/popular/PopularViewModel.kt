package com.hjalmarsson.fashion.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hjalmarsson.fashion.dagger.RepositoryComponent
import com.hjalmarsson.fashion.model.HeaderItem
import com.hjalmarsson.fashion.model.HeaderViewItemModel
import com.hjalmarsson.fashion.ui.popular.custom.RecommendedView
import com.hjalmarsson.fashion.ui.popular.custom.TopHeaderViewItem

class PopularViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance().reference.child("data")

    val liveData: LiveData<List<HeaderItem>> = MutableLiveData()

    init {
        RepositoryComponent.inject(this)
        getData()
    }

    private fun getData() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(e: DatabaseError) {}
            override fun onDataChange(snap: DataSnapshot) {

                (liveData as MutableLiveData).postValue(
                    snap.children.map {
                        it.getValue(HeaderItem::class.java) ?: HeaderItem()
                    }.toList()
                )
            }
        })
    }

}
