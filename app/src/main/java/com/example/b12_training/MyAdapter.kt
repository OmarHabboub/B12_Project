package com.example.b12_training

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.entryitem.view.*

class MyAdapter(val context : Context ,val entries: List<entry>) : RecyclerView.Adapter<MyAdapter.itemViewHolder>() {

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
    }

    override fun getItemCount(): Int = entries.size
}