package com.infotech.finnid.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.infotech.finnid.Activity.CartListActivity;
import com.infotech.finnid.ApiResponse.GetSalesVoucherDatum;
import com.infotech.finnid.R;
import com.infotech.finnid.Recharge.ApiRespose.OPDatumRes;
import com.infotech.finnid.Recharge.PrepaidRechageActivity;
import com.infotech.finnid.Utils.UtilMethods;

import java.util.ArrayList;


/**
 * Created by Uzair khan on 18-04-2020.
 */


public class OperatorAdapter extends RecyclerView.Adapter<OperatorAdapter.MyViewHolder> {

    private ArrayList<OPDatumRes> transactionsList;
    private Context mContext;
    Dialog dialog;
    TextView tv_operator;


    public class MyViewHolder extends RecyclerView.ViewHolder {

          TextView op_namne  ;

        public MyViewHolder(View view) {
            super(view);

            op_namne =  view.findViewById(R.id.op_namne);
        }
    }

    public OperatorAdapter(ArrayList<OPDatumRes> transactionsList, Context mContext , Dialog dialog , TextView tv_operator) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;
        this.tv_operator = tv_operator;
        this.dialog = dialog;

    }

    @Override
    public OperatorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_operator_adapter, parent, false);

        return new OperatorAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final OperatorAdapter.MyViewHolder holder, int position) {
        final OPDatumRes operator = transactionsList.get(position);

        holder.op_namne.setText(""+operator.getOperatorName());

        holder.op_namne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv_operator.setText(""+operator.getOperatorName() );
                dialog.dismiss();

                ((PrepaidRechageActivity) mContext).ItemClick_op(operator.getSpKey());


            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }



}