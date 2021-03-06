package com.floo.pedometer;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;



public class HomeActivity extends ActionBarActivity {

    ProgressBar progressBar;
    ImageButton chartButton,seedButton;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_kit);
        //setContentView(R.layout.home_image);

        progressBar = (ProgressBar) findViewById(R.id.myProgress);
        chartButton = (ImageButton) findViewById(R.id.chartButton);
        //centerButton= (ImageButton) findViewById(R.id.centerButton);
        seedButton = (ImageButton) findViewById(R.id.seedButton);

        progressBar.setRotation(135);

        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "secondaryProgress", 1, 100);
        animation.setDuration(2000); //in milliseconds
        //animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        ObjectAnimator animation2 = ObjectAnimator.ofInt(progressBar, "progress", 1, 250);
        animation2.setDuration(2000); //in milliseconds
        //animation2.setInterpolator(new DecelerateInterpolator());
        animation2.start();


        seedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SoundPlayer().execute();
                Intent i = new Intent(HomeActivity.this,SeedActivity.class);
                //i.putExtra("anim id in", R.anim.up_in);
                //i.putExtra("anim id out", R.anim.up_out);
                startActivity(i);
                //overridePendingTransition(R.anim.down_in, R.anim.down_out);

            }
        });

        /*chart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SoundPlayer().execute();
            }
        });*/
        chartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SoundPlayer().execute();

                Intent i = new Intent(HomeActivity.this,ChartActivity.class);
                //i.putExtra("anim id in", R.anim.up_in);
                //i.putExtra("anim id out", R.anim.up_out);
                startActivity(i);
                //overridePendingTransition(R.anim.down_in, R.anim.down_out);
            }
        });
    }

    private class SoundPlayer extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            mp = MediaPlayer.create(HomeActivity.this, Uri.parse("/system/media/audio/ui/Effect_Tick.ogg"));
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.release();
                }

            });
            mp.start();
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_webview_kit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
