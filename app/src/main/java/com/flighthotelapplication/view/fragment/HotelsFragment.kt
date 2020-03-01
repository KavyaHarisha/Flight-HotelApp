package com.flighthotelapplication.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.flighthotelapplication.R
import com.flighthotelapplication.data.remote.Resource
import com.flighthotelapplication.databinding.FragmentHotelsBinding
import com.flighthotelapplication.view.base.BaseFragment
import com.flighthotelapplication.viewmodel.HotelListViewModel


class HotelsFragment : BaseFragment<HotelListViewModel, FragmentHotelsBinding>() {
    override val getResourceLayout: Int
        get() = R.layout.fragment_hotels

    override fun getViewModel(): Class<HotelListViewModel> {
        return HotelListViewModel::class.java
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getResourceLayout, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.hotels.observe(this, Observer { hotleEntity ->
            run {
                if (hotleEntity != null && hotleEntity.status == Resource.Status.ERROR ||
                    hotleEntity.status == Resource.Status.SUCCESS
                ) dataBinding.loginProgress.visibility = View.GONE
                dataBinding.resource = hotleEntity
                if (null != dataBinding.hotelRecyclerView.adapter && dataBinding.hotelRecyclerView.adapter!!.itemCount > 0) {
                    dataBinding.errorText.visibility = View.GONE
                }
            }
        })
    }

}
