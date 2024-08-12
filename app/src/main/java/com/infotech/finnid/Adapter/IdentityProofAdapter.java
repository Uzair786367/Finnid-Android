package com.infotech.finnid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infotech.finnid.Objects.IdentityProofData;
import com.infotech.finnid.R;

import java.util.List;

public class IdentityProofAdapter extends ArrayAdapter<IdentityProofData> {
private Context mContext;
private List<IdentityProofData> mIdentityProofData;

public IdentityProofAdapter(Context context, List<IdentityProofData> identityProofDataList) {
        super(context, 0, identityProofDataList);
        mContext = context;
    mIdentityProofData = identityProofDataList;
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
        IdentityProofData identityProofData = mIdentityProofData.get(position);
        if (identityProofData != null) {
        textView.setText(identityProofData.getIdentityName());
        }

        return convertView;
        }
}

