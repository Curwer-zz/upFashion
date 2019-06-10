package com.hjalmarsson.fashion.ui.popular

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hjalmarsson.fashion.R
import com.hjalmarsson.fashion.extension.afterMeasure
import com.hjalmarsson.fashion.model.HeaderItem
import com.hjalmarsson.fashion.ui.popular.custom.RecommendedView
import com.hjalmarsson.fashion.ui.popular.custom.TopHeaderViewItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.popular_fragment.*
import com.hjalmarsson.fashion.model.HeaderViewItemModel

class PopularFragment : Fragment() {

    private lateinit var vm: PopularViewModel

    private val itemAdapter = ItemAdapter<AbstractItem<*, *>>()
    private val fastAdapter = FastAdapter.with<AbstractItem<*, *>, ItemAdapter<AbstractItem<*, *>>>(itemAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popular_fragment, container, false)
        vm = ViewModelProviders.of(this).get(PopularViewModel::class.java)

        vm.liveData.observe(viewLifecycleOwner, Observer {
            setData(it)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
    }

    private fun setData(data: List<HeaderItem>) {
        itemAdapter.clear()

        itemAdapter.add(TopHeaderViewItem(HeaderViewItemModel(data)) { openDetailsPage(it) })
        itemAdapter.add(RecommendedView("Recommended"))
        itemAdapter.add(RecommendedView("Sales"))
        itemAdapter.add(RecommendedView("Holiday fun"))
        itemAdapter.add(RecommendedView("New in shoes"))
    }

    private fun openDetailsPage(view: View) {
        val extras = FragmentNavigatorExtras(view to "details_background")
        findNavController()
            .navigate(
                PopularFragmentDirections.actionPopularFragmentToDetailsFragment().actionId,
                null,
                null,
                extras
            )
    }

    private fun setupRecycler() {
        recycler.apply {
            adapter = fastAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            afterMeasure { startPostponedEnterTransition() }

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return

                    val scrollOffset = recyclerView.computeVerticalScrollOffset()

                    layoutManager.findViewByPosition(0)?.y = -(scrollOffset * .2).toFloat()
                }
            })
        }
    }
}
