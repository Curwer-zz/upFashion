package com.hjalmarsson.fashion.ui.popular.custom

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem
import com.hjalmarsson.fashion.R
import com.hjalmarsson.fashion.util.MarginItemDecoration
import com.hjalmarsson.fashion.util.Util
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.recommended_view.view.*


class RecommendedView(private val text: String) : AbstractItem<RecommendedView, RecommendedView.ViewHolder>() {

    private val itemAdapter = ItemAdapter<AbstractItem<*, *>>()
    private val fastAdapter = FastAdapter.with<AbstractItem<*, *>, ItemAdapter<AbstractItem<*, *>>>(itemAdapter)

    override fun getType(): Int = R.id.item_type_recommended_view
    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)
    override fun getLayoutRes(): Int = R.layout.recommended_view

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.itemView.txt_title.text = text

        setupRecycler(holder)
        setData(holder)
    }

    private fun setData(holder: ViewHolder) {
        holder.itemView.v_recycler.adapter = fastAdapter
        for (i in 0 until 15) {
            itemAdapter.add(ProductItem())
        }
    }

    private fun setupRecycler(holder: ViewHolder) {
        holder.itemView.v_recycler.apply {
            layoutManager = LinearLayoutManager(holder.context).apply { this.orientation = LinearLayoutManager.HORIZONTAL }
            setHasFixedSize(true)
            addItemDecoration(MarginItemDecoration(Util.convertDpToPixel(this.context, 8F).toInt()))
            adapter = fastAdapter
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val context: Context
            get() = itemView.context
    }
}