package com.duet.jobdebugstation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duet.jobdebugstation.R;

import java.util.ArrayList;

public class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.MyViewHolder> {

    private ArrayList<Integer> imagesArrayList;
    private Context context;

    public InnerAdapter() {
        context = null;
        imagesArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null) context = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.inner_adapter_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setImage(position);
    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewInnerAdapter);
        }

        private void setImage(int position){
            imageView.setImageResource(imagesArrayList.get(position));
        }
    }

    public void setImagesArrayList(ArrayList<Integer> imagesArrayList) {
        this.imagesArrayList = imagesArrayList;
        notifyDataSetChanged();
    }
}
