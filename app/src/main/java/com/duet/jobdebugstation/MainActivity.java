package com.duet.jobdebugstation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.duet.jobdebugstation.Fragments.SleepStoriesFragment;
import com.duet.jobdebugstation.Fragments.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment welcomeFragment, sleepStoriesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadWelcomeFragment();
        loadSleepStoriesFragment();
    }

    private void loadWelcomeFragment(){
        if(welcomeFragment == null) welcomeFragment = new WelcomeFragment();
        loadFragment(welcomeFragment);
    }

    private void loadSleepStoriesFragment(){
        if(sleepStoriesFragment == null) sleepStoriesFragment = new SleepStoriesFragment();
        loadFragment(sleepStoriesFragment);
    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, fragment)
                .commit();
    }
}