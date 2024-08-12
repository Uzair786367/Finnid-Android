package com.infotech.finnid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infotech.finnid.Objects.CountryNameData;
import com.infotech.finnid.Objects.ShareTpeData;

import java.util.List;

public class CountryNameAdapter extends ArrayAdapter<CountryNameData> {
    private Context mContext;
    private List<CountryNameData> mCountryNameDataList;

    public CountryNameAdapter(Context context, List<CountryNameData> countryNameDataList) {
        super(context, 0, countryNameDataList);
        mContext = context;
        mCountryNameDataList = countryNameDataList;
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
        CountryNameData countryNameData = mCountryNameDataList.get(position);
        if (countryNameData != null) {
            textView.setText(countryNameData.getName());
        }

        return convertView;
    }

}
