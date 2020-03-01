package com.flighthotelapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.flighthotelapplication.BR
import com.flighthotelapplication.data.local.entity.FlightEntity
import com.flighthotelapplication.view.base.BaseAdapter

class FlightListAdapter : BaseAdapter<FlightListAdapter.FlightViewHolder, FlightEntity>() {
    var flightList: List<FlightEntity> = ArrayList()
    var itemLayout: Int = 0
    override fun setData(data: List<FlightEntity>) {
        flightList = data
    }

    override fun setLayout(layout: Int) {
        itemLayout = layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                inflater, itemLayout, parent,
                false
            )
        return FlightViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return flightList.size
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.bind(flightList[position])
    }

    class FlightViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Any) {
            binding.setVariable(BR.flight, item)
            binding.executePendingBindings()
        }
    }
}