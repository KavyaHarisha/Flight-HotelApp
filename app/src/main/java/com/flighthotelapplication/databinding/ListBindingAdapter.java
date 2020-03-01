package com.flighthotelapplication.databinding;


import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.flighthotelapplication.data.local.entity.FlightEntity;
import com.flighthotelapplication.data.remote.Resource;
import com.flighthotelapplication.utils.FragmentUtils;
import com.flighthotelapplication.view.base.BaseAdapter;

import java.util.List;

final class ListBindingAdapter {

    private ListBindingAdapter(){
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"resource","childLayout"})
    public static void setResource(RecyclerView recyclerView, Resource resource,Integer childLayout){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.getData() == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.getData());
            ((BaseAdapter)adapter).setLayout(childLayout);
        }
    }

    @BindingAdapter(value = "convertDate")
    static void setDate(TextView textView, String date){
        textView.setText(FragmentUtils.INSTANCE.convertDateString(date));
    }

    @BindingAdapter(value = "timeDifference")
    static void setDifferenceTime(TextView textView, FlightEntity entity){
        FragmentUtils utils = FragmentUtils.INSTANCE;
        textView.setText(utils.timeDifference(utils.convertDateString(entity.getDepartureDate()),
                utils.convertDateString(entity.getArrivalDate())));
    }

}
