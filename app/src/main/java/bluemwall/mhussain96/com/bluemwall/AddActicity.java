package bluemwall.mhussain96.com.bluemwall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;

public class AddActicity extends AppCompatActivity {

    public AdView adView;
    public AdsManager adsManager;
    public NativeExpressAdView nativeExpressAdView;
    public Button watchviseo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acticity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Watch Video");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Banner
        adsManager = new AdsManager(this);

        adView = (AdView) findViewById(R.id.myBanner);
        adsManager.LoadAdsBannerWithInterstitial(adView);

        // Native
        nativeExpressAdView = (NativeExpressAdView) findViewById(R.id.myNative);
        adsManager.LoadAdsNativeWithInterstitial(nativeExpressAdView);

        watchviseo = (Button) findViewById(R.id.submitbutton);

        watchviseo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adsManager.ShowInterstitial();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
        }
        return super.onOptionsItemSelected(item);
    }
}

