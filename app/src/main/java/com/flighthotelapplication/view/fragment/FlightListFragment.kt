package com.flighthotelapplication.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.flighthotelapplication.R
import com.flighthotelapplication.data.remote.Resource
import com.flighthotelapplication.databinding.FragmentFlightListBinding
import com.flighthotelapplication.view.base.BaseFragment
import com.flighthotelapplication.viewmodel.FlightListViewModel


class FlightListFragment : BaseFragment<FlightListViewModel, FragmentFlightListBinding>() {
    override val getResourceLayout: Int
        get() = R.layout.fragment_flight_list

    override fun getViewModel(): Class<FlightListViewModel> {
        return FlightListViewModel::class.java
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getResourceLayout, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.flights.observe(this, Observer { flightList ->
            if (null != flightList && flightList.status == Resource.Status.ERROR ||
                flightList.status == Resource.Status.SUCCESS
            ) {
                dataBinding.loginProgress.visibility = View.GONE
            }
            dataBinding.resource = flightList
            if (null != dataBinding.recyclerView.adapter && dataBinding.recyclerView.adapter!!.itemCount > 0) {
                dataBinding.errorText.visibility = View.GONE
            }

        })
    }
}
