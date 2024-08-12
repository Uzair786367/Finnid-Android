package com.infotech.finnid.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.infotech.finnid.R;
public class MemberMagmFragment extends Fragment {

    FrameLayout frameLayout;
    TextView memberReg, manageMem, nomineeMem;

    public MemberMagmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_member_magm, container, false);

        frameLayout = rootView.findViewById(R.id.frameLayout);
        memberReg = rootView.findViewById(R.id.memberReg);
        manageMem = rootView.findViewById(R.id.manageMem);
        nomineeMem = rootView.findViewById(R.id.nomineeMem);

        setBackgroundIm();
        memberReg.setBackground(getResources().getDrawable(R.drawable.rect_fill_new_colur));
        memberReg.setTextColor(getResources().getColor(R.color.black));





        loadFragment(new MemberRegistration());

        memberReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MemberRegistration());

                setBackgroundIm();
                memberReg.setBackground(getResources().getDrawable(R.drawable.rect_fill_new_colur));
                memberReg.setTextColor(getResources().getColor(R.color.black));

            }
        });

        manageMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ManageMember());
                setBackgroundIm();
                manageMem.setBackground(getResources().getDrawable(R.drawable.rect_fill_new_colur));
                manageMem.setTextColor(getResources().getColor(R.color.black));

            }
        });

        nomineeMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new NomineeMember());

                setBackgroundIm();
                nomineeMem.setBackground(getResources().getDrawable(R.drawable.rect_fill_new_colur));
                nomineeMem.setTextColor(getResources().getColor(R.color.black));
            }
        });

        return rootView;
    }

    private void setBackgroundIm() {

        manageMem.setBackgroundColor(getResources().getColor(R.color.white));
        memberReg.setBackgroundColor(getResources().getColor(R.color.white));
        nomineeMem.setBackgroundColor(getResources().getColor(R.color.white));

        manageMem.setTextColor(getResources().getColor(R.color.black));
        memberReg.setTextColor(getResources().getColor(R.color.black));
        nomineeMem.setTextColor(getResources().getColor(R.color.black));


    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

}
