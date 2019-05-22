package com.hjalmarsson.fashion.ui.popular.custom

import android.content.Context
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import com.hjalmarsson.fashion.R
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.product_item.view.*

class ProductItem : AbstractItem<ProductItem, ProductItem.ViewHolder>() {
    override fun getType(): Int = R.id.item_type_product_item
    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)
    override fun getLayoutRes(): Int = R.layout.product_item

    private val curveRadius = 20F

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)

        holder.itemView.v_img_product.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view!!.width, (view.height + curveRadius).toInt(), curveRadius)
            }
        }

        holder.itemView.v_img_product.clipToOutline = true
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val context: Context
            get() = itemView.context
    }
}