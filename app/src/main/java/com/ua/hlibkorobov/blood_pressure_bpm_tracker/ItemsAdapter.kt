package com.ua.hlibkorobov.blood_pressure_bpm_tracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class ItemsAdapter(
    private var items: MutableList<PressureRecord>,
    var context: Context,
    private var size: Int
) : RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val systolic: TextView = view.findViewById(R.id.itemListSystolic)
        val diastolic: TextView = view.findViewById(R.id.itemListDiastolic)
        val pulse: TextView = view.findViewById(R.id.itemListPulse)
        val date: TextView = view.findViewById(R.id.itemListDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.record_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return minOf(size, items.size)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.systolic.text = items[items.size - 1 - position].systolic.toString()
        holder.diastolic.text = items[items.size - 1 - position].diastolic.toString()
        holder.pulse.text = "Pulse: ${items[items.size - 1 - position].pulse} BMP"

        val formattedDate = SimpleDateFormat(
            "HH:mm, dd/MM/yyyy",
            Locale.getDefault()
        ).format(items[items.size - 1 - position].date!!)
        holder.date.text = formattedDate
    }
}