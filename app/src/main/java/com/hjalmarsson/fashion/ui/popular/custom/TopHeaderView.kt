package com.hjalmarsson.fashion.ui.popular.custom

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hjalmarsson.fashion.R
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.top_header_view.view.*
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.hjalmarsson.fashion.model.HeaderViewItemModel
import kotlinx.android.synthetic.main.list_item.view.*
import androidx.viewpager.widget.PagerAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.viewpager.widget.ViewPager
import com.hjalmarsson.fashion.misc.MyAnimatorListener
import com.hjalmarsson.fashion.model.HeaderItem
import com.hjalmarsson.fashion.util.Util


class TopHeaderViewItem(val headerViewItemModel: HeaderViewItemModel, val openProduct: (View) -> Unit) :
    AbstractItem<TopHeaderViewItem, TopHeaderViewItem.ViewHolder>() {
    override fun getType(): Int = R.id.item_type_top_header
    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)
    override fun getLayoutRes(): Int = R.layout.top_header_view

    private lateinit var myAdapter: MyAdapter
    private val dots = mutableListOf<AppCompatImageView>()

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.itemView.setPadding(0, Util.getStatusBarHeight(holder.context), 0, 0)

        holder.itemView.fab.setOnClickListener { openProduct.invoke(holder.itemView.root) }

        val margin = Util.convertDpToPixel(holder.context, 4f).toInt()
        val dimen = Util.convertDpToPixel(holder.context, 4f).toInt()

        dots.clear()
        headerViewItemModel.data.forEach {
            val dot = AppCompatImageView(holder.context)
            dot.setImageDrawable(ContextCompat.getDrawable(holder.context, R.drawable.dot))

            val params = ViewGroup.MarginLayoutParams(dimen, dimen).apply {
                this.leftMargin = margin
                this.rightMargin = margin
            }

            holder.itemView.dots_container.addView(dot, params)

            dots.add(dot)
        }

        setData(holder)
    }

    private fun setData(holder: ViewHolder) {
        myAdapter = MyAdapter(holder.context, headerViewItemModel.data)
        holder.itemView.pager.adapter = myAdapter
        holder.itemView.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                selectDot(holder.context, position)

                val animatorTitle = changeTextAnimation(
                    holder.itemView.v_title,
                    holder.itemView.v_title.height.toFloat()
                )

                val animatorPrice = changeTextAnimation(
                    holder.itemView.v_price,
                    holder.itemView.v_price.height.toFloat()
                )

                val animatorTitleBack = changeTextAnimation(
                    holder.itemView.v_title,
                    0f
                )

                val animatorPriceBack = changeTextAnimation(
                    holder.itemView.v_price,
                    0f
                )

                AnimatorSet().apply {
                    playTogether(listOf(animatorTitle, animatorPrice))
                    addListener(object : MyAnimatorListener() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)

                            holder.itemView.v_title.text = headerViewItemModel.data[position].productName
                            holder.itemView.v_price.text = headerViewItemModel.data[position].productPrice.toString()

                            val newSet = AnimatorSet()
                            newSet.playTogether(listOf(animatorTitleBack, animatorPriceBack))
                            newSet.start()
                        }
                    })

                    start()
                }
            }
        })

        selectDot(holder.context, 0)
        holder.itemView.v_title.text = headerViewItemModel.data[0].productName
    }

    fun changeTextAnimation(target: View, yValue: Float) = ObjectAnimator.ofFloat(
        target,
        View.TRANSLATION_Y,
        yValue
    ).apply {
        duration = 200
        interpolator = FastOutSlowInInterpolator()
    }

    private fun selectDot(context: Context, position: Int) {
        dots.forEachIndexed { index, view ->
            val from = view.width
            val to = if (index == position) Util.convertDpToPixel(context, 16f) else Util.convertDpToPixel(context, 5f)
            val anim = ValueAnimator
                .ofInt(from, to.toInt())
                .setDuration(200)

            anim.interpolator = FastOutSlowInInterpolator()
            anim.addUpdateListener {
                view.layoutParams.width = it.animatedValue as Int
                view.requestLayout()
            }
            anim.start()
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val context: Context
            get() = itemView.context
    }

    inner class MyAdapter(private val context: Context, val arrayList: List<HeaderItem>) : PagerAdapter() {

        override fun instantiateItem(parent: ViewGroup, position: Int): Any {
            val layout = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
            parent.addView(layout)
            //layout.v_title.text = arrayList[position].title
            //layout.v_img_product.setImageDrawable(ContextCompat.getDrawable(context, arrayList[position].productImage))

            return layout
        }

        override fun destroyItem(parent: ViewGroup, position: Int, view: Any) {
            parent.removeView(view as View)
        }

        override fun getCount(): Int = arrayList.size

        override fun getPageWidth(position: Int): Float = .9f

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`
    }

    /**
     * Use this later when viewPager 2 support page width
     */
    /*inner class MyAdapter(private val context: Context, val arrayList: ArrayList<HeaderViewItemModel>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        @NonNull
        override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(context).inflate(com.hjalmarsson.upfashion.R.layout.list_item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(@NonNull holder: MyViewHolder, position: Int) {
            holder.itemView.v_title.text = arrayList[position].title
            holder.itemView.v_img_product.setImageDrawable(ContextCompat.getDrawable(holder.context, arrayList[position].image))

        }

        override fun getItemCount(): Int {
            return arrayList.count()
        }

        inner class MyViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
            val context: Context
                get() = itemView.context
        }
    }*/
}
