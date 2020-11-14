package com.duet.jobdebugstation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duet.jobdebugstation.R;

import java.util.ArrayList;

public class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.MyViewHolder> {

    private ArrayList<Integer> imagesArrayList;
    private Context context;
    private final boolean singleCallType;

    public InnerAdapter(Boolean singleCallType) {
        context = null;
        imagesArrayList = new ArrayList<>();
        this.singleCallType = singleCallType;
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
        holder.setAnimation();
    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewInnerAdapter);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutInfoInner);
        }

        private void setImage(int position){
            imageView.setImageResource(imagesArrayList.get(position));
        }

        private void setAnimation(){
            if(singleCallType)
                relativeLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation_another));
        }
    }

    public void setImagesArrayList(ArrayList<Integer> imagesArrayList) {
        this.imagesArrayList = imagesArrayList;
        notifyDataSetChanged();
    }
}
