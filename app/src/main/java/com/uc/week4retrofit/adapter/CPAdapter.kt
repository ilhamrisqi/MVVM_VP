package com.uc.week4retrofit.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uc.week4retrofit.R
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.model.ProductionCompany



class CPAdapter(private val dataSet: List<ProductionCompany>) :
    RecyclerView.Adapter<CPAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ImageView : ImageView

        init {
            // Define click listener for the ViewHolder's View.
            ImageView = view.findViewById(R.id.imageView_CP)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_companyproduction, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
       Glide.with(viewHolder.ImageView.context).load(Const.IMG_URL + dataSet[position].logo_path)
           .into(viewHolder.ImageView)
    }

    override fun getItemCount() = dataSet.size
}


