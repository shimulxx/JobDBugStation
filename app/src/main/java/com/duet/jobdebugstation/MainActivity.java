package com.duet.jobdebugstation;

import androidx.appcompat.app.AlertDialog;
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

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements InnerAdapter.Work {

    private Fragment welcomeFragment, sleepStoriesFragment, sleepMusicFragment, beforePlayFragment, playerFragment;

    private Stack<Fragment> stack;

    private int TOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUtilities.initThreadPoolService();
        //loadWelcomeFragment();
        //loadSleepStoriesFragment();
        //loadSleepMusicFragment();
        //loadBeforePlayFragment(0);
        //loadPlayerFragment("ddd");
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadWelcomeFragment();
    }

    @Override
    protected void onDestroy() {
        AppUtilities.executorService.shutdown();
        super.onDestroy();
    }

    @Override
    public void loadBeforePlayFragment(int position, String name){
        AppUtilities.executorService.execute(() -> AppUtilities.handler.post(() -> {
            beforePlayFragment = new BeforePlayFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("key", position);
            bundle.putString("title", name);
            beforePlayFragment.setArguments(bundle);
            stack.push(beforePlayFragment);
            ++TOP;
            loadFragment(beforePlayFragment);
        }));

    }

    public void loadPlayerFragment(String title){
        AppUtilities.executorService.execute(() -> AppUtilities.handler.post(() -> {
            playerFragment = new PlayerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            playerFragment.setArguments(bundle);
            stack.push(playerFragment);
            ++TOP;
            loadFragment(playerFragment);
        }));
    }

    public void loadSleepMusicFragment(){
        AppUtilities.executorService.execute(() -> AppUtilities.handler.post(() -> {
            if(sleepMusicFragment == null) sleepMusicFragment = new SleepMusicFragment();
            ++TOP;
            stack.push(sleepMusicFragment);
            loadFragment(sleepMusicFragment);
        }));
    }

    private void loadWelcomeFragment(){
        AppUtilities.executorService.execute(() -> AppUtilities.handler.post(() -> {
            if(welcomeFragment == null) welcomeFragment = new WelcomeFragment();
            stack = new Stack<>();
            TOP = 1;
            stack.push(welcomeFragment);
            loadFragment(welcomeFragment);
        }));
    }

    public void loadSleepStoriesFragment(){
        AppUtilities.executorService.execute(() -> AppUtilities.handler.post(() -> {
            if(sleepStoriesFragment == null) sleepStoriesFragment = new SleepStoriesFragment();
            stack.push(sleepStoriesFragment);
            ++TOP;
            loadFragment(sleepStoriesFragment);
        }));
    }

    private void loadFragment(Fragment fragment){
        AppUtilities.executorService.execute(() -> AppUtilities.handler.post(() -> getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main, fragment)
                .commit()));
    }

    @Override
    public void onBackPressed() {
        AppUtilities.executorService.execute(() -> AppUtilities.handler.post(() -> {
            if(TOP == 1) showExitAlertDialog();
            else{
                stack.pop();
                loadFragment(stack.get(--TOP - 1));
            }
        }));
    }

    private void showExitAlertDialog(){
        AppUtilities.executorService.execute(() -> AppUtilities.handler.post(() -> new AlertDialog.Builder(MainActivity.this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", null)
                .show()));
    }
}