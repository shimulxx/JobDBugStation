package com.duet.jobdebugstation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duet.jobdebugstation.Adapters.InnerAdapter;
import com.duet.jobdebugstation.Model.ImageNameWithId;
import com.duet.jobdebugstation.R;

import java.util.ArrayList;

public class SleepMusicFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep_music_list_item, container, false);
        initRecView(view);
        return view;
    }

    private void initRecView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSleepMusic);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        InnerAdapter innerAdapter = new InnerAdapter(true, false);
        recyclerView.setAdapter(innerAdapter);
        innerAdapter.setImagesArrayList(createImageArrayList(20));
    }

    private ArrayList<ImageNameWithId> createImageArrayList(int numberOfItems){
        ArrayList<ImageNameWithId> imageArrayList = new ArrayList<>();
        for(int i = 1; i <= numberOfItems; ++i){
            imageArrayList.add(new ImageNameWithId(R.drawable.night_island_single, getResources().getString(R.string.night_island)));
            imageArrayList.add(new ImageNameWithId(R.drawable.sweet_sleep_single, getResources().getString(R.string.sweet_sleep)));
            imageArrayList.add(new ImageNameWithId(R.drawable.good_night_single, getResources().getString(R.string.good_night)));
            imageArrayList.add(new ImageNameWithId(R.drawable.moon_clouds_single, getResources().getString(R.string.moon_clouds)));
        }
        return imageArrayList;
    }
}
