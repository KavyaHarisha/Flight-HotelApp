package com.flighthotelapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.flighthotelapplication.BR

class RecyclerViewAdapter(val list: ObservableArrayList<*>, private val itemLayout: Int) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val imageBind = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, itemLayout, parent,
            false
        )
        return RecyclerViewHolder((imageBind))
    }

    class RecyclerViewHolder(val imageBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(imageBinding.root) {
        fun bindImage(image: Any) {
            imageBinding.setVariable(BR.obj, image)
            imageBinding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindImage(list[position])
    }
}