package com.deedsdevelopment.roomcounter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CounterListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CounterListAdapter.CounterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var counters = emptyList<Counter>() // Cached copy of counters

    inner class CounterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val counterItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return CounterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
        val current = counters[position]
        holder.counterItemView.text = current.counter
    }

    internal fun setCounters(counters: List<Counter>) {
        this.counters = counters
        notifyDataSetChanged()
    }

    override fun getItemCount() = counters.size
}