package com.flighthotelapplication.databinding;


import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flighthotelapplication.R;
import com.flighthotelapplication.data.local.entity.FlightEntity;
import com.flighthotelapplication.data.local.entity.HotelEntity;
import com.flighthotelapplication.data.remote.Resource;
import com.flighthotelapplication.utils.FragmentUtils;
import com.flighthotelapplication.view.adapter.RecyclerViewAdapter;

import java.util.List;

final class ListBindingAdapter {
    private ListBindingAdapter() {
    }

    @BindingAdapter(value = "convertDate")
    public static void setDate(TextView textView, String date) {
        textView.setText(FragmentUtils.INSTANCE.convertDateString(date));
    }

    @BindingAdapter(value = "timeDifference")
    public static void setDifferenceTime(TextView textView, FlightEntity entity) {
        FragmentUtils utils = FragmentUtils.INSTANCE;
        textView.setText(utils.timeDifference(utils.convertDateString(entity.getDepartureDate()),
                utils.convertDateString(entity.getArrivalDate())));
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"resource", "childLayoutItem"})
    public static void setRecycleResource(RecyclerView recyclerView, Resource resource, Integer childLayout) {
        if (resource == null || resource.getData() == null)
            return;
        ObservableArrayList<String> list = new ObservableArrayList<>();
        if (childLayout == R.layout.flight_row_item) {
            list.addAll((List) resource.getData());
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        } else if (childLayout == R.layout.image_item_layout) {
            HotelEntity entity = (HotelEntity) resource.getData();
            list.addAll(entity.getImages());
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL,
                    false));
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, childLayout);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter(value = "imageSet")
    public static void setImage(ImageView image, String url) {
        Glide.with(image.getContext()).load(url).into(image);
    }

    @BindingAdapter(value = "facilitiesSet")
    public static void setFacilitiesView(LinearLayout layout, Resource facilities) {
        GetHotelEntityData getHotelEntityData = new GetHotelEntityData(facilities).invoke();
        if (getHotelEntityData.is()) return;
        HotelEntity entity = getHotelEntityData.getEntity();
        List<String> facilityList = entity.getFacilities();
        layout.removeAllViews();
        if (facilityList != null && facilityList.size() > 0) {
            for (int i = 0; i < facilityList.size(); i++) {
                TextView textView = new TextView(layout.getContext());
                textView.setText(facilityList.get(i));
                textView.setTextSize(16);
                textView.setGravity(Gravity.CENTER);
                textView.setTypeface(Typeface.DEFAULT_BOLD);
                textView.setBackgroundResource(R.drawable.corner_drawable);
                textView.setTextColor(layout.getResources().getColor(R.color.light_green));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 10, 10, 10);
                textView.setLayoutParams(params);
                layout.addView(textView);
            }
        } else layout.setVisibility(View.GONE);
    }

    @BindingAdapter(value = {"hotelAttributes", "type"})
    public static void setTextDescription(TextView text, Resource resource, Integer type) {
        GetHotelEntityData getHotelEntityData = new GetHotelEntityData(resource).invoke();
        if (getHotelEntityData.is()) return;
        HotelEntity entity = getHotelEntityData.getEntity();
        if (type == 1)
            text.setText(entity.getDescription());
        else if (type == 2) {
            text.setText(entity.getHotelLocation());
            text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_location, 0, 0, 0);
        } else if (type == 3)
            text.setText(entity.getName());
    }

    @BindingAdapter(value = "rating")
    public static void setRating(RatingBar rate, Resource resource) {
        GetHotelEntityData getHotelEntityData = new GetHotelEntityData(resource).invoke();
        if (getHotelEntityData.is()) return;
        HotelEntity entity = getHotelEntityData.getEntity();
        rate.setRating(entity.getRating());
    }

    private static class GetHotelEntityData {
        private boolean myResult;
        private Resource facilities;
        private HotelEntity entity;

        public GetHotelEntityData(Resource facilities) {
            this.facilities = facilities;
        }

        boolean is() {
            return myResult;
        }

        public HotelEntity getEntity() {
            return entity;
        }

        public GetHotelEntityData invoke() {
            if (facilities == null || facilities.getData() == null) {
                myResult = true;
                return this;
            }
            entity = (HotelEntity) facilities.getData();
            myResult = false;
            return this;
        }
    }
}
