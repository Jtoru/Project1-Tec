package com.example.project1.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.project1.R
import com.example.project1.model.Restaurant
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso


class ListAdapter// Provide a suitable constructor (depends on the kind of dataset)
    (private val mDataset: List<Restaurant>, val callback: (Restaurant) -> Unit) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var mRestaurants=mDataset
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var name: TextView
        var number: TextView
        var image: ImageView?= null
        var cost: TextView?= null

        init {
            name = v.findViewById(R.id.txt_name_rest)
            number = v.findViewById(R.id.txt_number_rest)
            image = v.findViewById(R.id.img_logo_rest)
            cost = v.findViewById(R.id.img_cost_rest)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_row, parent, false)

        return MyViewHolder(v)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val rest = mRestaurants[position]
        holder.name.text = rest.name
        holder.number.text = rest.contactInfo.toString() + ""
        if(rest.images.isNotEmpty())
        {
            Picasso.get().load(rest.images[0]).error(R.drawable.logo_res).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.image)
        }
        else{
            Picasso.get().load(R.drawable.logo_res).into(holder.image)
        }
        if(rest.cost==0)
        {
           holder.cost?.text = "$"
        }
        if(rest.cost==1)
        {
            holder.cost?.text = "$$"
        }
        if(rest.cost==2)
        {
            holder.cost?.text = "$$$"
        }

        holder.itemView.setOnClickListener {
            callback(rest)
        }
    }

    override fun getItemCount(): Int {
        return mRestaurants.size

    }

    fun setRestaurants(restaurants:List<Restaurant>){
        mRestaurants = restaurants
        notifyDataSetChanged()
    }
}
