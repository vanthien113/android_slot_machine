package com.example.vanthienpro.androidslotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class HomeActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private ProgressBar progressBar;
    private Button newGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressBar = findViewById(R.id.pb_loadinghome);
        newGameBtn = findViewById(R.id.newGameBtn);

        MobileAds.initialize(this, "ca-app-pub-4925910216099050~1168186604");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4925910216099050/9098647514");

        showLoading();

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                // Check the LogCat to get your test device ID
//                .addTestDevice("05B32056A5A2E493EFBCDBC6BB0BF4FF")
//                .build();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e("THIEN", "ErrCode: " + i);
            }

            @Override
            public void onAdClosed() {
                hideLoading();
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        newGameBtn.setEnabled(false);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.GONE);
        newGameBtn.setEnabled(true);
    }

    void displayInterstitial() {
        mInterstitialAd.show();
    }

    public void onClickNewGameButton(View button) {
        Intent i = new Intent(this, MoneyActivity.class);
        startActivity(i);
    }

}
