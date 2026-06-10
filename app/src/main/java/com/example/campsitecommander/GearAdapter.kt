package com.example.campsitecommander

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GearAdapter(private val gearList: ArrayList<String>) :
    RecyclerView.Adapter<GearAdapter.GearViewHolder>() {

    class GearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGearItem: TextView = itemView.findViewById(R.id.tvGearItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GearViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gear, parent, false)
        return GearViewHolder(view)
    }

    override fun onBindViewHolder(holder: GearViewHolder, position: Int) {
        holder.tvGearItem.text = gearList[position]
    }

    override fun getItemCount(): Int = gearList.size

    fun removeItem(position: Int) {
        gearList.removeAt(position)
        notifyItemRemoved(position)
    }
}