package com.duet.jobdebugstation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duet.jobdebugstation.Adapters.InnerAdapter;
import com.duet.jobdebugstation.MainActivity;
import com.duet.jobdebugstation.Model.ImageNameWithId;
import com.duet.jobdebugstation.R;

import java.util.ArrayList;

public class BeforePlayFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.before_play_fragment_list_item, container, false);
        loadTitleImage(view);
        initRecView(view);
        setOnclickListerButton(view);
        return view;
    }

    private void setOnclickListerButton(View view){
        Button playButton  = view.findViewById(R.id.playButtonBeforePlay);
        MainActivity mainActivity = (MainActivity)getActivity();
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadPlayerFragment(getArguments().getString("title"));
            }
        });
    }

    private void loadTitleImage(View view){
        int position = getArguments().getInt("key");
        String title = getArguments().getString("title");
        ImageView titleImageView = view.findViewById(R.id.titleImageView);
        TextView musicName = view.findViewById(R.id.textMusicName);
        musicName.setText(title);
        position = position % 4;
        switch (position){
            case 0 : loadNightIsland(titleImageView); break;
            case 1 : loadSweetSleep(titleImageView); break;
            case 2 : loadGoodNight(titleImageView); break;
            case 3 : loadMoonClouds(titleImageView); break;
            default: break;
        }
    }

    private void loadNightIsland(ImageView titleImageView){
        titleImageView.setImageResource(R.drawable.night_island_large);
    }

    private void loadSweetSleep(ImageView titleImageView){
        titleImageView.setImageResource(R.drawable.sweet_sleep_large);
    }

    private void loadGoodNight(ImageView titleImageView){
        titleImageView.setImageResource(R.drawable.good_night_large);
    }

    private void loadMoonClouds(ImageView titleImageView){
        titleImageView.setImageResource(R.drawable.moon_clouds_large);
    }

    private void initRecView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recBeforePlayRelated);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        InnerAdapter innerAdapter = new InnerAdapter(true, true);
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
