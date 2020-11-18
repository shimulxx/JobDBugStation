package com.duet.jobdebugstation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.duet.jobdebugstation.MainActivity;
import com.duet.jobdebugstation.R;

public class WelcomeFragment extends Fragment {

    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment_list_item, container, false);
        initView(view);
        mainActivity = (MainActivity)getActivity();
        return view;
    }

    private void initView(View view){
        Button buttonGetStarted = view.findViewById(R.id.buttonGetStarted);
        setOnclickListener(buttonGetStarted);
    }

    private void setOnclickListener(Button buttonGetStarted){
        buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.loadSleepStoriesFragment();
            }
        });
    }
}
