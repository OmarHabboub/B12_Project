package com.example.b12_training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.class_item.view.*
import kotlinx.android.synthetic.main.entryitem.view.*

class classAdapter (
    val context: FragmentActivity?,
    val Classes: List<ClassInfo>)
 : RecyclerView.Adapter<classAdapter.itemViewHolder>() {

    class itemViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val className : TextView = itemView.className
        val classTime : TextView = itemView.classTime
        val classSec : TextView = itemView.classSec
        val prog : ProgressBar = itemView.progress

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.class_item,parent,false)
        return itemViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val currentItem = Classes[position]
        holder.className.text=currentItem.className
        holder.classTime.text =currentItem.classTime
        holder.classSec.text =currentItem.classSec
        holder.prog.setProgress(currentItem.progress)

    }

    override fun getItemCount(): Int = Classes.size

}
