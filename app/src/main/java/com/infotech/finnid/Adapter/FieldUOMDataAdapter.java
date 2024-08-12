package com.infotech.finnid.Adapter;

import android.content.Context;

import com.infotech.finnid.Objects.FieldUOMData;
import com.infotech.finnid.R;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;

public class FieldUOMDataAdapter extends ArrayAdapter<FieldUOMData> {
    private Context mContext;
    private List<FieldUOMData> mFieldUOMDataList;

    public FieldUOMDataAdapter(Context context, List<FieldUOMData> fieldUOMDataList) {
        super(context, 0, fieldUOMDataList);
        mContext = context;
        mFieldUOMDataList = fieldUOMDataList;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.tv_spin_name);
        FieldUOMData fieldUOMData = mFieldUOMDataList.get(position);
        if (fieldUOMData != null) {
            textView.setText(fieldUOMData.getUnit());
        }

        return convertView;
    }
}
