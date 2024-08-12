package com.infotech.finnid.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.infotech.finnid.R;

public class SellReportActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    TextView toolbar_text;
    ImageView toolbar_im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_purchase_report);

        GetId();

    }

    private void GetId() {

        toolbar_text = findViewById(R.id.toolbar_text);
        recycler_view = findViewById(R.id.recycler_view);
        toolbar_im = findViewById(R.id.toolbar_im);

        toolbar_text.setText("Sell Report");
        toolbar_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}