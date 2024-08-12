package com.infotech.finnid;

import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;

import com.google.android.material.tabs.TabLayout;
import com.infotech.finnid.Adapter.FragmentViewPagerAdapter;
import com.infotech.finnid.Fragments.AccountingFragment;
import com.infotech.finnid.Fragments.BankingFragment;
import com.infotech.finnid.Fragments.MemberMagmFragment;
import com.infotech.finnid.Fragments.NeoBankFragment;
import com.infotech.finnid.Fragments.RechargeFragment;
import com.infotech.finnid.Fragments.ReportFragment;
import com.infotech.finnid.Utils.PreferencesManager;

public class DashboardActivity extends AppCompatActivity {

    TabLayout sliding_tabs;
    ViewPager viewPager;
    FragmentViewPagerAdapter mFragmentViewPagerAdapter;
    LinearLayout memberMgm,fintech,section;
    ImageView memberMagm,arrowSection,arrowFintech;
    LinearLayout ll_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ll_logout = findViewById(R.id.ll_logout);
        sliding_tabs = findViewById(R.id.sliding_tabs);
        viewPager = findViewById(R.id.viewpager);

        mFragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        MemberMagmFragment memberMagmFragment = new MemberMagmFragment();
        mFragmentViewPagerAdapter.addFragment(memberMagmFragment, "Member Management");
        NeoBankFragment neoBankFragment = new NeoBankFragment();
        mFragmentViewPagerAdapter.addFragment(neoBankFragment,"Neo Bank");
        RechargeFragment rechargeFragment = new RechargeFragment();
        mFragmentViewPagerAdapter.addFragment(rechargeFragment,"Recharge and Bill Payment");
        AccountingFragment accountingFragment = new AccountingFragment();
        mFragmentViewPagerAdapter.addFragment(accountingFragment,"Accounting");
        BankingFragment bankingFragment = new BankingFragment();
        mFragmentViewPagerAdapter.addFragment(bankingFragment,"Banking");
        ReportFragment reportFragment = new ReportFragment();
        mFragmentViewPagerAdapter.addFragment(reportFragment,"Report");
        viewPager.setAdapter(mFragmentViewPagerAdapter);
        sliding_tabs.setupWithViewPager(viewPager);
        sliding_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        ll_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showLogoutConfirmationDialog();
            }
        });

    }
    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");

        // Add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked Yes button
                logout();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked No button
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void logout() {
        PreferencesManager preferencesManager = new PreferencesManager(this);
        preferencesManager.clear();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}