package com.duet.jobdebugstation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duet.jobdebugstation.Model.ImageNameWithId;
import com.duet.jobdebugstation.R;

import java.util.ArrayList;

public class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.MyViewHolder> {

    private ArrayList<ImageNameWithId> imagesArrayList;
    private Context context;
    private final boolean singleCallType, beforeCallType;

    public interface Work{
        void loadBeforePlayFragment(int position);
    }

    private Work work;

    public InnerAdapter(Boolean singleCallType, Boolean beforeCallType) {
        context = null;
        imagesArrayList = new ArrayList<>();
        this.singleCallType = singleCallType;
        this.beforeCallType = beforeCallType;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null) {
            context = parent.getContext();
            work = (Work)context;
        }
        if(beforeCallType)
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.inner_adapter_list_item_before, parent, false));
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.inner_adapter_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setImage(position);
        holder.setAnimation();
        holder.setOnclickListener(position);
        if(!beforeCallType) holder.setName(position);
    }

    @Override
    public int getItemCount() {
        return imagesArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final RelativeLayout relativeLayout;
        private final TextView textViewName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewInnerAdapter);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutInfoInner);
            textViewName = itemView.findViewById(R.id.textViewMusicName);
        }

        private void setOnclickListener(int position){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    work.loadBeforePlayFragment(position);
                }
            });
        }

        private void setImage(int position){
            imageView.setImageResource(imagesArrayList.get(position).getImageId());
        }

        private void setName(int position){
            textViewName.setText(imagesArrayList.get(position).getName());
        }

        private void setAnimation(){
            if(singleCallType)
                relativeLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation_another));
        }
    }

    public void setImagesArrayList(ArrayList<ImageNameWithId> imagesArrayList) {
        this.imagesArrayList = imagesArrayList;
        notifyDataSetChanged();
    }
}
