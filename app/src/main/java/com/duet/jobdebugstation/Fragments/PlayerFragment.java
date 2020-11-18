package com.duet.jobdebugstation.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.duet.jobdebugstation.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;



public class PlayerFragment extends Fragment {

    private SimpleExoPlayer player;
    private PlayerView playerView;

    private int currentWindow = 0;
    private long playBackPosition = 0;

    private boolean playWhenReady = true;

    private ImageButton exoPlay, exoPause;

    private TextView textViewTitle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_fragment_list_item, container, false);
        initView(view);
        setTitle();
        return view;
    }

    private void setTitle(){
        textViewTitle.setText(getArguments().getString("title"));
    }

    private void initView(View view){
        playerView = view.findViewById(R.id.exoPlayerViewContentFragment);
        exoPlay = playerView.findViewById(R.id.exo_play);
        exoPause = playerView.findViewById(R.id.exo_pause);
        textViewTitle = view.findViewById(R.id.textViewTitle);
    }

    private void initPlayer() {
        player = new SimpleExoPlayer.Builder(getActivity()).build();
        playerView.setPlayer(player);
        playerView.setControllerHideOnTouch(false);
        playerView.setControllerShowTimeoutMs(0);
        MediaSource mediaSource = buildMediaSourceSecond();
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playBackPosition);
        player.addMediaSource(mediaSource);
        player.prepare();
    }

    private MediaSource buildMediaSourceSecond()  {
        final RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(getContext());
        try{
            rawResourceDataSource.open(new DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.sample_file)));
        }catch (Exception e){ }

        MediaItem mediaItem = MediaItem.fromUri(rawResourceDataSource.getUri());

        DataSource.Factory factory = new DataSource.Factory() {
            @Override
            public DataSource createDataSource() {
                return rawResourceDataSource;
            }
        };

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(mediaItem);

        return mediaSource;
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(), "videoplaywork");
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(uri));
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playBackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            playBackPosition = currentWindow = 0;
            player = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) initPlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT < 24 || player == null) initPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) releasePlayer();
    }
}
