package com.infotech.finnid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infotech.finnid.Objects.CityListData;
import com.infotech.finnid.Objects.StateListData;

import java.util.List;

public class CityNameAdapter extends ArrayAdapter<CityListData> {
    private Context mContext;
    private List<CityListData> mCityNameAdapterList;

    public CityNameAdapter(Context context, List<CityListData> cityNameAdapterList) {
        super(context, 0, cityNameAdapterList);
        mContext = context;
        mCityNameAdapterList = cityNameAdapterList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (mContext == null) {
                mContext = parent.getContext();
            }
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        CityListData cityListData = mCityNameAdapterList.get(position);
        if (cityListData != null) {
            textView.setText(cityListData.getName());
        }

        return convertView;
    }
}
