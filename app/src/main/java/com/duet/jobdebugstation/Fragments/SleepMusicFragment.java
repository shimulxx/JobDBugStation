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
        InnerAdapter innerAdapter = new InnerAdapter(true);
        recyclerView.setAdapter(innerAdapter);
        innerAdapter.setImagesArrayList(createImageArrayList(20));
    }

    private ArrayList<Integer> createImageArrayList(int numberOfItems){
        ArrayList<Integer> imageArrayList = new ArrayList<>();
        for(int i = 1; i <= numberOfItems; ++i){
            imageArrayList.add(R.drawable.night_island);
            imageArrayList.add(R.drawable.sweet_sleep);
        }
        return imageArrayList;
    }
}
