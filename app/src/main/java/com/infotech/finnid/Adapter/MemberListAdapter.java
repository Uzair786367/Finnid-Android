package com.infotech.finnid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.infotech.finnid.Activity.EditMemberRegistrationActivity;
import com.infotech.finnid.Activity.NomineeRegistrationActivity;
import com.infotech.finnid.Activity.UploadActivity;
import com.infotech.finnid.Activity.ViewProfileRegistrationActivity;
import com.infotech.finnid.Objects.MemberData;
import com.infotech.finnid.R;

import java.util.ArrayList;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MyViewHolder>  {
    private  ArrayList<MemberData> memberDataArrayList;
    private Context context;

    public MemberListAdapter(ArrayList<MemberData> memberDataArrayList, Context context) {
        this.memberDataArrayList = memberDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MemberListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_member_list, parent, false);
        return new MemberListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberListAdapter.MyViewHolder holder, int position) {
        final MemberData memberData = memberDataArrayList.get(position);
        holder.memberIdValue.setText(memberData.getMemberId());
        holder.memberFName.setText(memberData.getMemFirstName()+""+memberData.getMemMiddileName()+""+memberData.getMemLastName());
        holder.birthPlace.setText(memberData.getBirthPlace());
        holder.panNumber.setText(memberData.getPan());
        holder.aadharNumber.setText(memberData.getAdhar());
        holder.date.setText(memberData.getDob());

        Log.e("panno","no : "+memberData.getPan().toString());

        if(memberData.getPan().toString().trim().equalsIgnoreCase("")){

            holder.Status.setText("FORM-60 NOT UPLOADED");
            holder.Status.setTextColor(context.getResources().getColor(R.color.red));

        }else {

            holder.Status.setText("SHARE APPLICATION FORM NOT UPLOADED");
            holder.Status.setTextColor(context.getResources().getColor(R.color.yallow));

        }

        holder.view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(context , ViewProfileRegistrationActivity.class);
                ii.putExtra("operator", "" + new Gson().toJson(memberData).toString());
                context.startActivity(ii);
            }
        });

         holder.edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(context , EditMemberRegistrationActivity.class);
                ii.putExtra("operator", "" + new Gson().toJson(memberData).toString());
                context.startActivity(ii);

            }
        });

         holder.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent ii = new Intent(context , UploadActivity.class);
                ii.putExtra("operator", "" + new Gson().toJson(memberData).toString());
                ii.putExtra("memberId", "" +  memberData.getId().toString());
                context.startActivity(ii);


                //   startActivity(new Intent(getActivity(), NomineeRegistrationActivity.class));



            }
        });

         holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

         holder.nominee_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(context , NomineeRegistrationActivity.class);
                ii.putExtra("memberId", "" +  memberData.getMemberId().toString());
                context.startActivity(ii);


            }
        });





    }

    @Override
    public int getItemCount() {
        return memberDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView memberIdValue,createdDate,memberFName,memMName,memLName,birthPlace,panNumber,aadharNumber,date ,
                Status;
        LinearLayout view_profile , edit_profile , upload , download ,nominee_registration;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            memberIdValue = itemView.findViewById(R.id.memberIdValue);
            createdDate = itemView.findViewById(R.id.createdDate);
            memberFName = itemView.findViewById(R.id.memberFName);
            memMName = itemView.findViewById(R.id.memMName);
            memLName = itemView.findViewById(R.id.memLName);
            birthPlace = itemView.findViewById(R.id.birthPlace);
            panNumber = itemView.findViewById(R.id.panNumber);
            aadharNumber = itemView.findViewById(R.id.aadharNumber);
            Status = itemView.findViewById(R.id.Status);
            date = itemView.findViewById(R.id.date);
            view_profile = itemView.findViewById(R.id.view_profile);
            upload = itemView.findViewById(R.id.upload);
            edit_profile = itemView.findViewById(R.id.edit_profile);
            download = itemView.findViewById(R.id.download);
            nominee_registration = itemView.findViewById(R.id.nominee_registration);


        }
    }
}
