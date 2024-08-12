package com.infotech.finnid.SqliteDatabase;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infotech.finnid.R;

import java.util.ArrayList;

// Extends the Adapter class to RecyclerView.Adapter
// and implement the unimplemented methods
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList  courseName;
    Context context;

    // Constructor for initialization
    public Adapter(Context context,   ArrayList courseName) {
        this.context = context;
         this.courseName = courseName;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        // Passing view to ViewHolder
        Adapter.ViewHolder viewHolder = new Adapter.ViewHolder(view);
        return viewHolder;
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        // TypeCast Object to int type

        holder.tv_item.setText((String) courseName.get(position));
    }

    @Override
    public int getItemCount() {
        // Returns number of items
        // currently available in Adapter

        Log.e("courseName","courseName :  "+courseName.size() );

        return courseName.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView tv_item;

        public ViewHolder(View view) {
            super(view);
            tv_item = (TextView) view.findViewById(R.id.tv_item);
        }
    }
}

