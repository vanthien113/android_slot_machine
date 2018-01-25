package com.example.vanthienpro.androidslotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MoneyActivity extends AppCompatActivity {
    private AdView mAdView;
    private int currentFunds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int i) {
                Toast.makeText(MoneyActivity.this, "Load Ad Fail", Toast.LENGTH_SHORT).show();
            }
        });

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        if (i.hasExtra("currentFunds")) {
            currentFunds = extras.getInt("currentFunds");
        } else {
            currentFunds = 0;
        }

    }

    public void onFiveButtonClicked(View button) {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 5 + currentFunds);
        startActivity(i);
    }

    public void onTenButtonClicked(View button) {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 10 + currentFunds);
        startActivity(i);
    }

    public void onTwentyButtonClicked(View button) {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 20 + currentFunds);
        startActivity(i);
    }

    public void onFiftyButtonClicked(View button) {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("playerFunds", 50 + currentFunds);
        startActivity(i);
    }
}
