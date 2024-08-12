package com.infotech.finnid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.infotech.finnid.Adapter.FragmentViewPagerAdapter;
import com.infotech.finnid.Fragments.AccountingFragment;
import com.infotech.finnid.Fragments.BankingFragment;
import com.infotech.finnid.Fragments.MemberMagmFragment;
import com.infotech.finnid.Fragments.NeoBankFragment;
import com.infotech.finnid.Fragments.RechargeFragment;
import com.infotech.finnid.Fragments.ReportFragment;
import com.infotech.finnid.Fragments.ServiesHomeFragment;
import com.infotech.finnid.Utils.PreferencesManager;

public class DashboardActivityNew extends AppCompatActivity  implements View.OnClickListener {

    LinearLayout homeView , supportView , reportView  , ll_logout;
    ImageView im_home , im_supportView,im_reportView, im_logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_new);

        getid();


    }

    private void getid() {


        homeView = findViewById(R.id.homeView);
        supportView = findViewById(R.id.supportView);
        ll_logout = findViewById(R.id.ll_logout);
        reportView = findViewById(R.id.reportView);
        im_home = findViewById(R.id.im_home);
        im_supportView = findViewById(R.id.im_supportView);
        im_reportView = findViewById(R.id.im_reportView);
        im_logout = findViewById(R.id.im_logout);

        homeView.setOnClickListener(this);
        supportView.setOnClickListener(this);
        ll_logout.setOnClickListener(this);
        reportView.setOnClickListener(this);


        setColoueIcan();
        im_home.setColorFilter(this.getResources().getColor(R.color.colorPrimary));
        loadFragment(new ServiesHomeFragment());
    }

    private void setColoueIcan() {

         im_home.setColorFilter(this.getResources().getColor(R.color.color_grey_dark));
        im_supportView.setColorFilter(this.getResources().getColor(R.color.color_grey_dark));
        im_reportView.setColorFilter(this.getResources().getColor(R.color.color_grey_dark));
        im_logout.setColorFilter(this.getResources().getColor(R.color.color_grey_dark));


    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View view) {


        if(view==homeView){

            setColoueIcan();
            im_home.setColorFilter(this.getResources().getColor(R.color.colorPrimary));

            loadFragment(new ServiesHomeFragment());

        }

         if(view==supportView){

             setColoueIcan();
             im_supportView.setColorFilter(this.getResources().getColor(R.color.colorPrimary));
        }

         if(view==reportView){

             setColoueIcan();
             im_reportView.setColorFilter(this.getResources().getColor(R.color.colorPrimary));
        }

         if(view==ll_logout){

             setColoueIcan();
             im_logout.setColorFilter(this.getResources().getColor(R.color.colorPrimary));

             showLogoutConfirmationDialog();
        }


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