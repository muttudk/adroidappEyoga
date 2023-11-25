package com.ntpl.eyoga.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.ButtonSemiBold;
import com.ntpl.eyoga.font.TextViewRegular;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.SQLiteHandlerDisease;
import com.ntpl.eyoga.helper.SQLiteHandlerReport;
import com.ntpl.eyoga.helper.YouTubeConfig;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DiseaseDetailsActivity extends YouTubeBaseActivity {
    private static final String TAG = DiseaseDetailsActivity.class.getSimpleName();
    ImageView back;
    ButtonSemiBold benefitsButton,sideEffectsButton;
    YouTubeThumbnailView videoThumbnailImageView;
    YouTubePlayer player;
    SQLiteHandlerDisease db;
    SQLiteHandlerReport dbReport;
    String asanaName,asanaVideo,diseaseName, dietString,rulesString,benefitsString,sideEffectsString;
    TextViewSemiBold asanaNameTV,title;
    TextViewRegular diet,rules,benefits,sideEffects;
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_details);

        db = new SQLiteHandlerDisease(this);
        dbReport = new SQLiteHandlerReport(this);

        back = findViewById(R.id.back);
        diet = findViewById(R.id.diet);
        rules = findViewById(R.id.rules);
        benefits = findViewById(R.id.benefits);
        sideEffects = findViewById(R.id.sideEffects);
        benefitsButton = findViewById(R.id.benefitsButton);
        sideEffectsButton = findViewById(R.id.sideEffectsButton);

        back.setOnClickListener(view -> {
            int currentTimeMillis = player.getCurrentTimeMillis();

//            Date c = Calendar.getInstance().getTime();
//            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
//            String formattedDate = df.format(c);
            String formattedDate = df.format(new Date());

            long minutes = TimeUnit.MILLISECONDS.toMinutes(currentTimeMillis);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis);

//            dbReport.insertData(asanaName, formattedDate, minutes+":"+seconds);
            dbReport.insertData(asanaName, formattedDate, currentTimeMillis+"");
            finish();
        });

        Intent intent = getIntent();
        if (intent != null) {
            diseaseName = intent.getStringExtra("diseaseName");
        }

        Cursor cursor = db.getSingleRow(diseaseName);
        if(cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    asanaName = cursor.getString(0);
                    asanaVideo = cursor.getString(1);
                    dietString = cursor.getString(2);
                    rulesString = cursor.getString(3);
                    benefitsString = cursor.getString(4);
                    sideEffectsString = cursor.getString(5);
                } while (cursor.moveToNext());
            }
        }

        asanaNameTV = findViewById(R.id.asanaName);
        title = findViewById(R.id.title);
        youTubePlayerView = findViewById(R.id.youtube_player_view);

        asanaNameTV.setText(asanaName);
        title.setText(diseaseName);
        diet.setText(dietString);
        rules.setText(rulesString);
        benefits.setText(benefitsString);
        sideEffects.setText(sideEffectsString);

//        asanaNameTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(DiseaseDetailsActivity.this, AndroidDatabaseManager.class);
//                startActivity(intent1);
//            }
//        });

        sideEffectsButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(DiseaseDetailsActivity.this, SideEffectsActivity.class);
            intent1.putExtra("sideEffects",sideEffectsString);
            startActivity(intent1);
        });

        benefitsButton.setOnClickListener(view -> {
            Intent intent1 = new Intent(DiseaseDetailsActivity.this, BenefitsActivity.class);
            intent1.putExtra("benefits",benefitsString);
            startActivity(intent1);
        });

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

                    player = youTubePlayer;

//                    long millis = youTubePlayer.getCurrentTimeMillis();

//                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
//                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

                    //load the video
                    youTubePlayer.loadVideo(asanaVideo);


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


        videoThumbnailImageView = findViewById(R.id.video_thumbnail_image_view);
        videoThumbnailImageView.initialize(YouTubeConfig.getApiKey(), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is sucess, set the video id to thumbnail to load
//                youTubeThumbnailLoader.setVideo(youtubeVideoModel.getVideoId());
                youTubeThumbnailLoader.setVideo(asanaVideo);


                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
//                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed
//                Log.e(TAG, "Youtube Initialization Failure");

            }
        });

        videoThumbnailImageView.setOnClickListener(view -> {

            Intent intent1 = new Intent(DiseaseDetailsActivity.this, YoutubePlayerActivity.class);
            intent1.putExtra("video_id", asanaVideo);
            startActivity(intent1);

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int currentTimeMillis = player.getCurrentTimeMillis();

        Date c = Calendar.getInstance().getTime();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = df.format(c);
        String formattedDate = df.format(new Date());

        long minutes = TimeUnit.MILLISECONDS.toMinutes(currentTimeMillis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis);

//        dbReport.insertData(asanaName, formattedDate, minutes+":"+seconds);
        dbReport.insertData(asanaName, formattedDate, currentTimeMillis+"");
    }
}