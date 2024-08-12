package com.infotech.finnid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infotech.finnid.Objects.AddressProofData;
import com.infotech.finnid.Objects.IdentityProofData;
import com.infotech.finnid.R;

import java.util.List;

public class AddressProofAdapter extends ArrayAdapter<AddressProofData> {
    private Context mContext;
    private List<AddressProofData> mAddressProofData;

    public AddressProofAdapter(Context context, List<AddressProofData> addressProofDataList) {
        super(context, 0, addressProofDataList);
        mContext = context;
        mAddressProofData = addressProofDataList;
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
        AddressProofData addressProofData = mAddressProofData.get(position);
        if (addressProofData != null) {
            textView.setText(addressProofData.getAddresProofName());
        }

        return convertView;
    }
}
