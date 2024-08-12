package com.infotech.finnid.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infotech.finnid.Activity.ManageMemberActivity;
import com.infotech.finnid.Activity.MemberRegistrationActivity;
import com.infotech.finnid.Activity.NomineeRegistrationActivity;
import com.infotech.finnid.Activity.PurchaseReportActivity;
import com.infotech.finnid.Activity.PurchaseVoucherActivity;
import com.infotech.finnid.Activity.SellReportActivity;
import com.infotech.finnid.Activity.SellVoucherActivity;
import com.infotech.finnid.R;
import com.infotech.finnid.Recharge.PrepaidRechageActivity;


public class ServiesHomeFragment extends Fragment  implements View.OnClickListener {

    LinearLayout li_member_register , li_manageMember,li_nominee , purchase_voucher , sell_voucher;
    LinearLayout li_mobile , li_dth, li_Electricity , li_Gas , li_water , li_FASTag , li_Insurance,
            li_Broadband , li_landline , li_Postpaid , li_Water , li_purchase_report , li_sell_report;

    TextView txtMarquee;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_servies_home, container, false);

        GetId(v);

        return v;

    }


    private void GetId(View v) {

        li_mobile = v.findViewById(R.id.li_mobile);
        li_dth = v.findViewById(R.id.li_dth);
        li_Electricity = v.findViewById(R.id.li_Electricity);
        li_Gas = v.findViewById(R.id.li_Gas);
        li_water = v.findViewById(R.id.li_water);
        li_Broadband = v.findViewById(R.id.li_Broadband);
        li_Insurance = v.findViewById(R.id.li_Insurance);
        li_landline = v.findViewById(R.id.li_landline);
        li_Postpaid = v.findViewById(R.id.li_Postpaid);
        li_Water = v.findViewById(R.id.li_Water);
        li_FASTag = v.findViewById(R.id.li_FASTag);
        li_purchase_report = v.findViewById(R.id.li_purchase_report);
        li_sell_report = v.findViewById(R.id.li_sell_report);
        li_purchase_report.setOnClickListener(this);
        li_sell_report.setOnClickListener(this);


        li_member_register = v.findViewById(R.id.li_member_register);
        li_manageMember = v.findViewById(R.id.li_manageMember);
        li_nominee = v.findViewById(R.id.li_nominee);
        purchase_voucher = v.findViewById(R.id.purchase_voucher);
        sell_voucher = v.findViewById(R.id.sell_voucher);
        txtMarquee = v.findViewById(R.id.txtMarquee);
        li_member_register.setOnClickListener(this);
        li_manageMember.setOnClickListener(this);
        li_nominee.setOnClickListener(this);
        purchase_voucher.setOnClickListener(this);
        sell_voucher.setOnClickListener(this);



        li_mobile.setOnClickListener(this);
      //  li_dth.setOnClickListener(this);
      //  li_Electricity.setOnClickListener(this);
      //  li_Gas.setOnClickListener(this);
       // li_water.setOnClickListener(this);
      //  li_Broadband.setOnClickListener(this);
     //   li_Insurance.setOnClickListener(this);
     //   li_landline.setOnClickListener(this);
        li_Postpaid.setOnClickListener(this);
       // li_Water.setOnClickListener(this);
      //  li_FASTag.setOnClickListener(this);

        txtMarquee.setSelected(true);

    }


    @Override
    public void onClick(View view) {


        if(view==li_mobile){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Mobile");
            ii.putExtra("serviceName","Prepaid");
            ii.putExtra("serviceId","1");
            startActivity(ii);

        }


        if(view==li_dth){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Dth");
            startActivity(ii);

        }

         if(view==li_Electricity){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Electricity");
            startActivity(ii);

        }


       if(view==li_Gas){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Gas");
            startActivity(ii);

        }


          if(view==li_water){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Water");
            startActivity(ii);

        }


          if(view==li_Broadband){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Broadband");
            startActivity(ii);

        }

        if(view==li_Insurance){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Insurance");
            startActivity(ii);

        }

         if(view==li_landline){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Landline");
            startActivity(ii);

        }

        if(view==li_Postpaid){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Postpaid");
            ii.putExtra("serviceName","Postpaid");
            ii.putExtra("serviceId","2");
            startActivity(ii);

        }

        if(view==li_Water){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","Water");
            startActivity(ii);

        }


        if(view==li_FASTag){

            Intent ii = new Intent(getActivity(), PrepaidRechageActivity.class);
            ii.putExtra("type","FASTag");
            startActivity(ii);

        }

       if(view==li_sell_report){

            startActivity(new Intent(getActivity(), SellReportActivity.class));

        }

         if(view==li_purchase_report){

            startActivity(new Intent(getActivity(), PurchaseReportActivity.class));

        }

         if(view==sell_voucher){

            startActivity(new Intent(getActivity(), SellVoucherActivity.class));

        }




  if(view==purchase_voucher){

            startActivity(new Intent(getActivity(), PurchaseVoucherActivity.class));

        }

        if(view==li_member_register){

            startActivity(new Intent(getActivity(), MemberRegistrationActivity.class));

        }

         if(view==li_manageMember){

             startActivity(new Intent(getActivity(), ManageMemberActivity.class));

        }
    }
}