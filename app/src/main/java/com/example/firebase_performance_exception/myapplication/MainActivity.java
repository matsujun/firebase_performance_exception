package com.example.firebase_performance_exception.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "FirebaseException";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPicasso();

        ImageView image1 = (ImageView)findViewById(R.id.image1);
        ImageView image2 = (ImageView)findViewById(R.id.image2);

        Picasso.with(getApplicationContext())
                .load("https://www.google.co.jp/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png")
                .into(image1);

        Picasso.with(getApplicationContext())
                .load("https://www.google.co.jp/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png?")
                .into(image2);
    }

    private void setupPicasso() {
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttp3Downloader downloader = new OkHttp3Downloader(okHttpClient);

        Picasso.Builder picassoBuilder = new Picasso.Builder(getApplicationContext());
        picassoBuilder.downloader(downloader);
        picassoBuilder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.e(TAG, "Image load failed : "+uri);
                exception.printStackTrace();
            }
        });

        Picasso.setSingletonInstance(picassoBuilder.build());
    }

}
