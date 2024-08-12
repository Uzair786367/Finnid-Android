package com.infotech.finnid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.infotech.finnid.ApiResponse.GetSalesVoucherDatum;
import com.infotech.finnid.R;
import com.infotech.finnid.Utils.UtilMethods;

import java.util.ArrayList;




/**
 * Created by Uzair khan on 18-04-2020.
 */


public class ItemizedAdapter extends RecyclerView.Adapter<ItemizedAdapter.MyViewHolder> {

    private ArrayList<GetSalesVoucherDatum> transactionsList;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {

         public ImageView im_logo;
         TextView taq ,tv_amount, tv_productname;
         ImageView im_cart_add;


        public MyViewHolder(View view) {
            super(view);


            im_logo =  view.findViewById(R.id.im_logo);
            taq =  view.findViewById(R.id.taq);
            tv_productname =  view.findViewById(R.id.tv_productname);
            tv_amount =  view.findViewById(R.id.tv_amount);
            im_cart_add =  view.findViewById(R.id.im_cart_add);

        }
    }

    public ItemizedAdapter(ArrayList<GetSalesVoucherDatum> transactionsList, Context mContext  ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;

    }

    @Override
    public ItemizedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sales_voucher_adapter, parent, false);

        return new ItemizedAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ItemizedAdapter.MyViewHolder holder, int position) {
        final GetSalesVoucherDatum operator = transactionsList.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.new_logo);
        requestOptions.error(R.drawable.new_logo);

        Glide.with(mContext)
                .load( ""+operator.getProductImage())
                .apply(requestOptions)
                .into(holder.im_logo);

        holder.taq.setText("TAQ "+operator.getTaq()+" UNIT");
        holder.tv_productname.setText(""+operator.getItemname()+"");
        holder.tv_amount.setText("₹ "+operator.getMrpPerUnit()+"/- "+operator.getUomdetails());

        holder.im_cart_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UtilMethods.INSTANCE.SaleCartAdd(mContext , operator.getItemcode(),1,operator.getSaleprice());

            }
        });


    }


    @Override
    public int getItemCount() {
        return transactionsList.size();
    }



}