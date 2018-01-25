package com.example.vanthienpro.androidslotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class GameOverActivity extends AppCompatActivity {
    private RewardedVideoAd mRewardedVideoAd;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        progressBar = findViewById(R.id.pb_loadingFree);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
    }

    public void onAddMoneyClicked(View button) {
        Intent i = new Intent(this, MoneyActivity.class);
        startActivity(i);
    }

    public void onQuitClicked(View button) {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    private void navigationGameActivity(int item) {
        Intent intent = new Intent(this, GameActivity.class);

        intent.putExtra("playerFunds", item);

        startActivity(intent);
    }

    public void onFreeClicked(View view) {
        progressBar.setVisibility(View.VISIBLE);

        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());

        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                progressBar.setVisibility(View.GONE);
                mRewardedVideoAd.show();
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                navigationGameActivity(rewardItem.getAmount());
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }
        });
    }
}
