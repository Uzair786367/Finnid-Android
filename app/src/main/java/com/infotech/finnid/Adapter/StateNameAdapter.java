package com.infotech.finnid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infotech.finnid.Objects.StateListData;

import java.util.List;

public class StateNameAdapter extends ArrayAdapter<StateListData> {
  private Context mContext;
  private List<StateListData> mStateNameAdapterList;

  public StateNameAdapter(Context context, List<StateListData> stateNameAdapterList) {
    super(context, 0, stateNameAdapterList);
    mContext = context;
    mStateNameAdapterList = stateNameAdapterList;
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
        StateListData stateListData = mStateNameAdapterList.get(position);
        if (stateListData != null) {
            textView.setText(stateListData.getName());
        }

        return convertView;
    }
}
