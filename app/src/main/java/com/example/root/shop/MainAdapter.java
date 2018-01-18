package com.example.root.shop;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 1/18/18.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<String> mDataSet;
    private ArrayList<Integer>images;

    public MainAdapter(ArrayList<String> mDataSet, ArrayList<Integer> images) {
        this.mDataSet = mDataSet;
        this.images=images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mtitle.setText(mDataSet.get(position));
        holder.mbackground.setBackgroundResource(images.get(position));
        holder.mbackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent=new Intent(view.getContext(),info_act.class);
                    view.getContext().startActivity(intent);
                }
                catch (Exception ex){
                    throw  ex;
                }

            }
        });
    }
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mtitle;
        CardView mbackground;
        public ViewHolder(View itemView) {
            super(itemView);
            mtitle=itemView.findViewById(R.id.info_text);
          mbackground=itemView.findViewById(R.id.card_view);
        }
    }
}
