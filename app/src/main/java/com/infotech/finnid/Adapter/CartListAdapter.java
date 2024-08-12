package com.infotech.finnid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {

    private ArrayList<GetSalesVoucherDatum> transactionsList;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {

          TextView Qty , Rate , Disc , Amt , TV , GST , CESS , Total;
          ImageView delete;


        public MyViewHolder(View view) {
            super(view);

            Qty =  view.findViewById(R.id.Qty);
            Total =  view.findViewById(R.id.Total);
            Rate =  view.findViewById(R.id.Rate);
            Disc =  view.findViewById(R.id.Disc);
            Amt =  view.findViewById(R.id.Amt);
            TV =  view.findViewById(R.id.TV);
            GST =  view.findViewById(R.id.GST);
            CESS =  view.findViewById(R.id.CESS);
            delete =  view.findViewById(R.id.delete);


        }
    }

    public CartListAdapter(ArrayList<GetSalesVoucherDatum> transactionsList, Context mContext  ) {

        this.transactionsList = transactionsList;
        this.mContext = mContext;

    }

    @Override
    public CartListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_list_adapter, parent, false);

        return new CartListAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final CartListAdapter.MyViewHolder holder, int position) {
        final GetSalesVoucherDatum operator = transactionsList.get(position);

        holder.Qty.setText(""+operator.getQty());
        holder.Rate.setText(""+operator.getAmount());
        holder.Disc.setText("0");
        holder.Amt.setText(""+operator.getAmount());
        holder.TV.setText(""+operator.getAmount());
        holder.GST.setText("0" );
        holder.CESS.setText("0" );
        holder.Total.setText(""+operator.getAmount());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilMethods.INSTANCE.Delete_Cart(mContext , operator.getItemId(),1);
            }
        });

    }


    @Override
    public int getItemCount() {
        return transactionsList.size();
    }



}