package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.ntpl.eyoga.R;
import com.ntpl.eyoga.helper.YouTubeConfig;

import java.util.concurrent.TimeUnit;

public class YoutubePlayerActivity extends YouTubeBaseActivity {

    private static final String TAG = YoutubePlayerActivity.class.getSimpleName();
    private String videoID;
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        //get the video id
        videoID = getIntent().getStringExtra("video_id");
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        initializeYoutubePlayer();

    }

    /**
     * initialize the youtube player
     */
    private void initializeYoutubePlayer() {
        youTubePlayerView.initialize(YouTubeConfig.getApiKey(), new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                boolean wasRestored) {

                //if initialization success then load the video id to youtube player
                if (!wasRestored) {
                    //set the player style here: like CHROMELESS, MINIMAL, DEFAULT
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
//                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

                    long millis = youTubePlayer.getCurrentTimeMillis();

                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

                    //load the video
                    youTubePlayer.loadVideo(videoID);


                    //OR

                    //cue the video
                    //youTubePlayer.cueVideo(videoID);

                    //if you want when activity start it should be in full screen uncomment below comment
                    //  youTubePlayer.setFullscreen(true);

                    //If you want the video should play automatically then uncomment below comment
                    youTubePlayer.play();

                    //If you want to control the full screen event you can uncomment the below code
                    //Tell the player you want to control the fullscreen change
                    /*youTubePlayer.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                    //Tell the player how to control the change
                    youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean arg0) {
                            // do full screen stuff here, or don't.
                            Log.e(TAG,"Full screen mode");
                        }
                    });*/

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                //print or show error if initialization failed
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });
    }


}
