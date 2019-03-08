package com.example.evitegif.adapter

import android.content.Context
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.evitegif.R
import kotlinx.android.synthetic.main.item_gift_list.view.*

/**
 * Adapter class for populating gif display RecyclerView
 */
class GifSearchAdapter(context: Context) : RecyclerView.Adapter<GifSearchAdapter.ViewHolder>() {

    private val mContext: Context = context
    private val STROKE_WIDTH = 5f
    private val CENTER_RADIUS = 30f
    private var alGif = ArrayList<String>()

    var currentOffset: Int = 0

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gift_list, p0, false))
    }

    override fun getItemCount(): Int {
        return alGif.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (position >= currentOffset) {
            val circularProgressDrawable = CircularProgressDrawable(mContext)
            circularProgressDrawable.strokeWidth = STROKE_WIDTH
            circularProgressDrawable.centerRadius = CENTER_RADIUS
            circularProgressDrawable.start()

            Glide.with(mContext)
                .load(alGif.get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(circularProgressDrawable)
                .into(viewHolder.gifImage)

        }
    }

    /**
     * set the gif list data and
     */
    fun setDataInvalidate(alGif: ArrayList<String>) {
        this.alGif = alGif
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var gifImage: ImageView = view.iv_gif
    }
}