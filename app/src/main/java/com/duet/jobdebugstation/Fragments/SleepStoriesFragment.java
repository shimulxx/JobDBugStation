package com.duet.jobdebugstation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.duet.jobdebugstation.Adapters.MusicOuterAdapter;
import com.duet.jobdebugstation.Model.MusicItem;
import com.duet.jobdebugstation.R;

import java.util.ArrayList;

public class SleepStoriesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sleep_stories_fragment_list_item, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        ArrayList<MusicItem> musicItemArrayList = createMusicItemArrayList();
        recyclerViewWork(view, musicItemArrayList);
    }

    private ArrayList<MusicItem> createMusicItemArrayList(){
        ArrayList<MusicItem> musicItemArrayList = new ArrayList<>();
        musicItemArrayList.add(new MusicItem(true, R.drawable.item_single_rec, null));
        ArrayList<Integer> imageArrayList = new ArrayList<>();
        for(int i = 1; i <= 10; ++i){
            imageArrayList.add(R.drawable.night_island);
            imageArrayList.add(R.drawable.sweet_sleep);
        }
        musicItemArrayList.add(new MusicItem(false, -1, imageArrayList));
        return musicItemArrayList;
    }

    private void recyclerViewWork(View view, ArrayList<MusicItem> musicItemArrayList){
        RecyclerView recyclerView = view.findViewById(R.id.sleep_stories_rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        MusicOuterAdapter musicOuterAdapter = new MusicOuterAdapter();
        recyclerView.setAdapter(musicOuterAdapter);
        musicOuterAdapter.setMusicItems(musicItemArrayList);
        recyclerView.smoothScrollToPosition(0);
        /*recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollToPosition(0);
            }
        }, 10);*/
    }
}
