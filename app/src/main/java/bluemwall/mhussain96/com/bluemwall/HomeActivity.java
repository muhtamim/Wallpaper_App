package bluemwall.mhussain96.com.bluemwall;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.firebase.auth.FirebaseAuth;

import bluemwall.mhussain96.com.bluemwall.Adapter.MyFragmentAdapter;
import bluemwall.mhussain96.com.bluemwall.Common.Common;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    public AdView adView;
    public AdsManager adsManager;
    public NativeExpressAdView nativeExpressAdView;


    DrawerLayout drawer;

    NavigationView navigationView;

    BottomNavigationView menu_bottom;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Common.PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "You need accept this permission to download image", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Common.SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Snackbar.make(drawer, new StringBuilder("Welcome ").append(FirebaseAuth.getInstance().getCurrentUser()
                        .getEmail().toString()), Snackbar.LENGTH_LONG).show();

                //Runtime permission request
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Common.PERMISSION_REQUEST_CODE);
                }

                viewPager = findViewById(R.id.viewPager);
                MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), this);
                viewPager.setAdapter(adapter);

                tabLayout = findViewById(R.id.tabLayout);
                tabLayout.setupWithViewPager(viewPager);

                loadUserInformation();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("blue M wall");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);

        // Banner
        adsManager = new AdsManager(this);

        adView = (AdView) findViewById(R.id.myBanner);
        adsManager.LoadAdsBannerWithInterstitial(adView);

        // Native
        nativeExpressAdView = (NativeExpressAdView) findViewById(R.id.myNative);
        adsManager.LoadAdsNativeWithInterstitial(nativeExpressAdView);

//        menu_bottom = findViewById(R.id.navigation);
//        menu_bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.action_news) {
//                    startActivity(new Intent(HomeActivity.this, AddActicity.class));
//                }
//                return false;
//            }
//        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        //check if not sign-in then navigate Sign-in page
//        if (FirebaseAuth.getInstance().getCurrentUser() == null){
//            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), Common.SIGN_IN_REQUEST_CODE);
//
//        }
//        else {
//            Snackbar.make(drawer, new StringBuilder("Welcome ").append(FirebaseAuth.getInstance().getCurrentUser()
//                    .getEmail().toString()),Snackbar.LENGTH_LONG).show();
//
//        }

        //Runtime permission request
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Common.PERMISSION_REQUEST_CODE);
        }

        viewPager = findViewById(R.id.viewPager);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        loadUserInformation();

    }

    private void loadUserInformation() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            View headerLayout = navigationView.getHeaderView(0);
            TextView txt_email = headerLayout.findViewById(R.id.txt_email);
            txt_email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.home, menu);
        getMenuInflater().inflate(R.menu.bottom_navigation_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(item.getItemId() == R.id.action_news){
            Intent intent = new Intent(HomeActivity.this, AddActicity.class);
            startActivity(intent);
            return true;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_gallery) {
//            // Handle the camera action
//            adsManager.ShowInterstitial();
//            return true;
//        }
        if (id == R.id.nav_slideshow) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
            dialog.setTitle(R.string.aboutus);
            dialog.setContentView(R.layout.my_dialog_layout);
            dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.iconm);
            dialog.show();
        }


        if (id == R.id.nav_about) {
            // Handle the camera action
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Blue M Wall");
            String shareMessage = "\nMy All favourite  wallpaper is here, check it out.\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));

        }

        if (id == R.id.Privacy_Policy) {
            // Handle the camera action
            String YourPageURL = "https://bluemwall.blogspot.com/2020/01/privacy-policy.html?m=1";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));

            startActivity(browserIntent);

        }
        if (id == R.id.Instagram) {
            // Handle the camera action
            String YourPageURL = "https://www.instagram.com/m_hussain_96/";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));

            startActivity(browserIntent);

        }
        if (id == R.id.Facebook) {
            // Handle the camera action
            String YourPageURL = "https://www.facebook.com/mukabbirhussain6";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));

            startActivity(browserIntent);

        }
        if (id == R.id.Youtube) {
            // Handle the camera action
            String YourPageURL = "https://www.youtube.com/mukabbirhussain";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
            startActivity(browserIntent);

        }

        if (id == R.id.Rate_Us) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


}
