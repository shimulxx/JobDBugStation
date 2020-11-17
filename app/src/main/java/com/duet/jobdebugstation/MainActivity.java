package com.duet.jobdebugstation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.duet.jobdebugstation.Adapters.InnerAdapter;
import com.duet.jobdebugstation.Fragments.BeforePlayFragment;
import com.duet.jobdebugstation.Fragments.PlayerFragment;
import com.duet.jobdebugstation.Fragments.SleepMusicFragment;
import com.duet.jobdebugstation.Fragments.SleepStoriesFragment;
import com.duet.jobdebugstation.Fragments.WelcomeFragment;

public class MainActivity extends AppCompatActivity implements InnerAdapter.Work {

    private Fragment welcomeFragment, sleepStoriesFragment, sleepMusicFragment, beforePlayFragment, playerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadWelcomeFragment();
        //loadSleepStoriesFragment();
        //loadSleepMusicFragment();
        //loadBeforePlayFragment(0);
        loadPlayerFragment();
    }

    @Override
    public void loadBeforePlayFragment(int position){
        beforePlayFragment = new BeforePlayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("key", position);
        beforePlayFragment.setArguments(bundle);
        loadFragment(beforePlayFragment);
    }

    private void loadPlayerFragment(){
        playerFragment = new PlayerFragment();
        loadFragment(playerFragment);
    }

    private void loadSleepMusicFragment(){
        if(sleepStoriesFragment == null) sleepMusicFragment = new SleepMusicFragment();
        loadFragment(sleepMusicFragment);
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