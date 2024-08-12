package com.infotech.finnid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.infotech.finnid.Fragments.ItemizedFragment;
import com.infotech.finnid.Fragments.OpenFragment;
import com.infotech.finnid.R;

public class SellVoucherActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView toolbar_im;
    TextView toolbar_text;
     LinearLayout li_itemized , li_open;
    View v_line_itemized , v_line_open;
    ImageView im_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sell_voucher);

        GetId();

    }

    private void GetId() {

        toolbar_text = findViewById(R.id.toolbar_text);
        toolbar_im = findViewById(R.id.toolbar_im);
        im_cart = findViewById(R.id.im_cart);

        v_line_open = findViewById(R.id.v_line_open);
        v_line_itemized = findViewById(R.id.v_line_itemized);

        li_itemized = findViewById(R.id.li_itemized);
        li_open = findViewById(R.id.li_open);


        toolbar_text.setText("Sell Voucher");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        li_itemized.setOnClickListener(this);
        li_open.setOnClickListener(this);
        im_cart.setOnClickListener(this);

        loadFragment(new ItemizedFragment());

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

         if(view==li_itemized){

             loadFragment(new ItemizedFragment());

             v_line_itemized.setVisibility(View.VISIBLE);
             v_line_open.setVisibility(View.INVISIBLE);

        }

        if(view==li_open){

            loadFragment(new OpenFragment());

            v_line_itemized.setVisibility(View.INVISIBLE);
            v_line_open.setVisibility(View.VISIBLE);
        }
    if(view==im_cart){

        startActivity(new Intent(SellVoucherActivity.this, CartListActivity.class));


        }
    }
}