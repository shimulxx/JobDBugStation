package com.duet.jobdebugstation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duet.jobdebugstation.Adapters.InnerAdapter;
import com.duet.jobdebugstation.R;

import java.util.ArrayList;

public class BeforePlayFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.before_play_fragment_list_item, container, false);
        loadTitleImage(view);
        initRecView(view);
        return view;
    }
    private void loadTitleImage(View view){
        int position = getArguments().getInt("key");
        ImageView titleImageView = view.findViewById(R.id.titleImageView);
        TextView musicName = view.findViewById(R.id.textMusicName);
        if(position % 2 == 0) {
            titleImageView.setImageResource(R.drawable.night_island_large);
            musicName.setText(getResources().getString(R.string.night_island));
        }
        else {
            titleImageView.setImageResource(R.drawable.sweet_sleep_large);
            musicName.setText(getResources().getString(R.string.sweet_sleep));
        }
    }

    private void initRecView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recBeforePlayRelated);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        InnerAdapter innerAdapter = new InnerAdapter(true, true);
        recyclerView.setAdapter(innerAdapter);
        innerAdapter.setImagesArrayList(createImageArrayList(20));
    }

    private ArrayList<Integer> createImageArrayList(int numberOfItems){
        ArrayList<Integer> imageArrayList = new ArrayList<>();
        for(int i = 1; i <= numberOfItems; ++i){
            imageArrayList.add(R.drawable.night_island_single);
            imageArrayList.add(R.drawable.sweet_sleep_single);
        }
        return imageArrayList;
    }
}
