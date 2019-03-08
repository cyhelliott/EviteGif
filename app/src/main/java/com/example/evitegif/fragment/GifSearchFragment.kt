package com.example.evitegif.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.evitegif.adapter.GifSearchAdapter
import com.example.evitegif.viewmodel.GifSearchViewModel
import kotlinx.android.synthetic.main.fragment_search_gif.*


class GifSearchFragment : Fragment() {

    private lateinit var gifSearchAdapter: GifSearchAdapter
    private lateinit var viewModel: GifSearchViewModel
    var gifOffset: Int = 0

    private val GIF_LOAD_NUM = 25

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = GifSearchViewModel()
        return inflater.inflate(com.example.evitegif.R.layout.fragment_search_gif, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        gifSearchAdapter = GifSearchAdapter(context!!)
        rv_gif_display.adapter = gifSearchAdapter

        viewModel = ViewModelProviders.of(this).get(GifSearchViewModel::class.java)

        setViewModelObserver()
        setListener()

        rv_gif_display.setLayoutManager(GridLayoutManager(context, 2))

        //add scroll listener to load more gifs at bottom
        rv_gif_display.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager
                //bottom of list!
                if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == gifSearchAdapter.itemCount - 1) {
                    gifOffset += GIF_LOAD_NUM
                    viewModel.populateGifArray(edt_search.text.toString(), gifOffset)
                }
            }
        })
    }

    /**
     * Sets observer for ViewModel
     */
    private fun setViewModelObserver() {
        viewModel.gifList.observe(this, Observer<ArrayList<String>> { price: ArrayList<String>? ->
            // Will update adapter data and invalidate
            gifSearchAdapter.setDataInvalidate(viewModel.gifList.value!!)
        })
    }

    /**
     * Sets listeners
     */
    private fun setListener() {
        btn_search.setOnClickListener { v: View ->
            // Resetting gif offset and gif list
            gifOffset = 0
            viewModel.gifList.value?.clear()
            // Will search giphy and update ViewModel LiveData
            viewModel.populateGifArray(edt_search.text.toString(), gifOffset)
        }
    }

}
