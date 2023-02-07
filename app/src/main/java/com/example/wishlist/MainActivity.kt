package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), WishlistAdapter.ItemClickListener {
    lateinit var wishlist_items: List<Wishlist>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val submit = findViewById(R.id.submit) as Button

        // Lookup the RecyclerView in activity layout
        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)
        // Fetch the list of emails
        wishlist_items = WishlistFetcher.getWishlistItems()



        // Create adapter passing in the list of emails
        val adapter = WishlistAdapter(wishlist_items,this)
        // Attach the adapter to the RecyclerView to populate items
        wishlistRv.adapter = adapter
        // Set layout manager to position the items
        wishlistRv.layoutManager = LinearLayoutManager(this)

        submit.setOnClickListener {
            val itemName = findViewById(R.id.itemName) as EditText
            val price = findViewById(R.id.price) as EditText
            val url = findViewById(R.id.url) as EditText

            val item = Wishlist(itemName.text.toString(),price.text,url.text.toString())
            (wishlist_items as MutableList<Wishlist>).add(item)
            wishlistRv.layoutManager = LinearLayoutManager(this)
            adapter.notifyDataSetChanged()
            itemName.setText("")
            price.setText("")
            url.setText("")
        }

    }
    override fun onItemClick(position: Int) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(wishlist_items[position].URL))
            ContextCompat.startActivity(this, browserIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Invalid URL for " + wishlist_items[position].name, Toast.LENGTH_LONG).show()
        }
    }
}