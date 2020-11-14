package com.duet.jobdebugstation.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duet.jobdebugstation.Model.MusicItem;
import com.duet.jobdebugstation.R;

import java.util.ArrayList;

public class MusicOuterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<MusicItem> musicItems;

    private Context context;

    private final int SINGLE_TYPE = 1;
    private final int MULTI_TYPE = 0;

    public MusicOuterAdapter() {
        musicItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null) context = parent.getContext();
        if(viewType == SINGLE_TYPE)
            return new SingleViewHolder(LayoutInflater.from(context).inflate(R.layout.single_rec_view_list_item, parent, false));
        else if (viewType == MULTI_TYPE)
            return new MultiViewHolder(LayoutInflater.from(context).inflate(R.layout.multi_rec_view_list_item, parent, false));

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MultiViewHolder) {
            ((MultiViewHolder)holder).setRecyclerView(position);
            ((MultiViewHolder)holder).setMultiAnimation();
        }
        else if(holder instanceof SingleViewHolder){
            ((SingleViewHolder)holder).setSingleAnimation();
            ((SingleViewHolder)holder).setImageView(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return musicItems.get(position).isSingleType() ? SINGLE_TYPE : MULTI_TYPE;
    }

    @Override
    public int getItemCount() {
        return musicItems.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final RelativeLayout relativeLayout;

        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewSingle);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutSingle2);
        }

        private void setImageView(int position){
            imageView.setImageResource(musicItems.get(position).getSingleId());
        }

        private void setSingleAnimation(){
            relativeLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));
        }
    }

    class MultiViewHolder extends RecyclerView.ViewHolder{

        private final RecyclerView recyclerView;
        private final RelativeLayout relativeLayout;


        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recViewMulti);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutMulti);
        }

        private void setRecyclerView(int position){
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            InnerAdapter innerAdapter = new InnerAdapter();
            recyclerView.setAdapter(innerAdapter);
            innerAdapter.setImagesArrayList(musicItems.get(position).getImageArrayList());
        }

        private void setMultiAnimation(){
            relativeLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));
        }
    }

    public void setMusicItems(ArrayList<MusicItem> musicItems) {
        this.musicItems = musicItems;
        notifyDataSetChanged();
    }
}
