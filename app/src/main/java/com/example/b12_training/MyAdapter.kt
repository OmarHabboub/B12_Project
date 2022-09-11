package com.example.b12_training

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.entryitem.view.*
import retrofit2.Callback

class MyAdapter(
    val context: FragmentActivity?,
    val entries: List<entry>,
    val onEntryClickListener: OnEntryClickListener
) : RecyclerView.Adapter<MyAdapter.itemViewHolder>() {

    class itemViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val API : TextView = itemView.API
        val Desc : TextView = itemView.Description
        val Cate : TextView = itemView.Category

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.entryitem,parent,false)
        return itemViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val currentItem = entries[position]
        holder.API.text="API $position: " + currentItem.API
        holder.Desc.text ="Description $position: "+currentItem.Description
        holder.Cate.text ="Category $position: "+ currentItem.Category

        holder.itemView.setOnClickListener {
            onEntryClickListener.OnEntryClicked(position)
        }
    }

    override fun getItemCount(): Int = entries.size
}