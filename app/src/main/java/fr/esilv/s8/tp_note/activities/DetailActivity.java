package fr.esilv.s8.tp_note.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;

import fr.esilv.s8.tp_note.Constants;
import fr.esilv.s8.tp_note.R;
import fr.esilv.s8.tp_note.models.Details;
import fr.esilv.s8.tp_note.models.Item;
import fr.esilv.s8.tp_note.models.ParseData;

public class DetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String AUTHOR = "AUTHOR";
    private static final String ID = "ID";
    private static final String TITLE = "TITLE";
    private static final String PUBLISHDATE = "PUBLISHDATE";

    private static final String YOUTUBE_DETAIL_URL = "https://www.googleapis.com/youtube/v3/videos?part=statistics&id=";

    public String VIDEO_ID;

    private TextView title;
    private TextView description;
    private TextView date;
    private TextView author;
    private TextView view;
    private TextView like;
    private TextView dislike;

    public static void start(Context context, Item item) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(TITLE, item.getSnippet().getTitle());
        intent.putExtra(AUTHOR, item.getSnippet().getChannelTitle());

        intent.putExtra(ID, item.getId().getVideoId());
        intent.putExtra(DESCRIPTION, item.getSnippet().getDescription());
        intent.putExtra(PUBLISHDATE, item.getSnippet().getPublishedAt());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        date = (TextView) findViewById(R.id.date);
        author = (TextView) findViewById(R.id.author);
        view = (TextView) findViewById(R.id.view);
        like = (TextView) findViewById(R.id.like);
        dislike = (TextView) findViewById(R.id.dislike);

        title.setText(getIntent().getStringExtra(TITLE));
        description.setText(getIntent().getStringExtra(DESCRIPTION));
        date.setText(getIntent().getStringExtra(PUBLISHDATE));
        author.setText(getIntent().getStringExtra(AUTHOR));

        VIDEO_ID = getIntent().getStringExtra("ID");

        getDetails();


        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(Constants.API_KEY, this);


    }

    private void getDetails() {

        final StringRequest contractsRequest = new StringRequest(YOUTUBE_DETAIL_URL + VIDEO_ID + "&key=" + Constants.API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Details parseData = new Gson().fromJson(response, Details.class);

                view.setText("View\n" + parseData.getItems().get(0).getStatistics().getViewCount());
                like.setText("Like\n" + parseData.getItems().get(0).getStatistics().getLikeCount());
                dislike.setText("Dislike\n" + parseData.getItems().get(0).getStatistics().getDislikeCount());
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Contracts", "Error");
            }
        });
        Volley.newRequestQueue(this).add(contractsRequest);
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };
}
