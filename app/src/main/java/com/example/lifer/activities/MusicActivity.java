package com.example.lifer.activities;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lifer.R;

public class MusicActivity extends Activity implements View.OnClickListener {
    private MediaPlayer mediaPlayer;
    private Button playPauseButton;
    private Button prevButton, nextButton;
    private SeekBar seekBar;
    private TextView songTitleTextView, artistTextView;
    private int[] songList = {R.raw.song1, R.raw.song2, R.raw.song3, R.raw.song4};
    private String[] songTitles = {"The Sound Of Slience", "幻昼", "黄昏", "夏日蝉鸣"};
    private String[] artists = {"Bandari", "墨水小天", "周传雄", "顾客勋"};
    private int currentSongIndex = 0;
    private Handler handler;
    private Runnable runnable;

    private boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        playPauseButton = findViewById(R.id.play_pause_button);
        prevButton = findViewById(R.id.prev_button);
        nextButton = findViewById(R.id.next_button);
        seekBar = findViewById(R.id.seek_bar);
        songTitleTextView = findViewById(R.id.song_title_text_view);
        artistTextView = findViewById(R.id.artist_text_view);

        playPauseButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        mediaPlayer = MediaPlayer.create(this, songList[currentSongIndex]);
        songTitleTextView.setText(songTitles[currentSongIndex]);
        artistTextView.setText(artists[currentSongIndex]);

        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    seekBar.setProgress(currentPosition);
                }
                handler.postDelayed(this, 1000); // Update seek bar every second
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_pause_button:
                togglePlayPause();
                break;
            case R.id.prev_button:
                playPreviousSong();
                break;
            case R.id.next_button:
                playNextSong();
                break;
        }
    }

    private void togglePlayPause() {
        isPlaying = !isPlaying;
        playPauseButton.setSelected(isPlaying);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
            handler.postDelayed(runnable, 1000);
        }
    }

    private void playPreviousSong() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        } else {
            currentSongIndex = songList.length - 1;
        }
        playSongAtIndex(currentSongIndex);
    }

    private void playNextSong() {
        if (currentSongIndex < songList.length - 1) {
            currentSongIndex++;
        } else {
            currentSongIndex = 0;
        }
        playSongAtIndex(currentSongIndex);
    }

    private void playSongAtIndex(int index) {
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, songList[index]);
        songTitleTextView.setText(songTitles[index]);
        artistTextView.setText(artists[index]);
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
//        playPauseButton.setImageResource(R.drawable.bofangb);
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(runnable);
    }
}
