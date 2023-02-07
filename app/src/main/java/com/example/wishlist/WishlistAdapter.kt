package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private var wishlist_items: List<Wishlist>, private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {


    interface ItemClickListener {

        fun onItemClick(position: Int)
    }




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val NameTextView: TextView
        val PriceTextView: TextView
        val URLTextView: TextView


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            NameTextView = itemView.findViewById(R.id.NameTextView)
            PriceTextView = itemView.findViewById(R.id.PriceTextView)
            URLTextView = itemView.findViewById(R.id.URLTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return wishlist_items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        var wishlist_item = wishlist_items.get(position)
        // Set item views based on views and data model
        holder.NameTextView.text = wishlist_item.name
        holder.PriceTextView.text = wishlist_item.price.toString()
        holder.URLTextView.text = wishlist_item.URL
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
        holder.itemView.setOnLongClickListener {
            var mutableItems = wishlist_items.toMutableList()
            mutableItems.remove(wishlist_item)
            wishlist_items = mutableItems.toList()
            notifyItemRemoved(position)
            return@setOnLongClickListener true
        }

    }


}





