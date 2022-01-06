package com.example.searchncovi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    //    ViewPager viewPager;
    private int INTERNET_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ViewPager2 viewPager2 = findViewById(R.id.view_pager2);
        viewPager2.setAdapter(new PagerFragment(this));
        tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        tab.setText("Policy");
                        tab.setIcon(R.drawable.ic_baseline_policy_24);
                        BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(
                                ContextCompat.getColor(getApplicationContext(), R.color.purple_200)
                        );
                        badgeDrawable.setVisible(true);
                        badgeDrawable.setNumber(3);
                        badgeDrawable.setMaxCharacterCount(3);
                        break;
                    }
                    case 1: {
                        tab.setText("Statistical");
                        tab.setIcon(R.drawable.ic_baseline_place_24);
                        BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(
                                ContextCompat.getColor(getApplicationContext(), R.color.purple_200)
                        );
                        badgeDrawable.setVisible(true);
                        badgeDrawable.setNumber(64);
                        badgeDrawable.setMaxCharacterCount(3);
                        break;
                    }
                    case 2: {
                        tab.setText("About us");
                        tab.setIcon(R.drawable.ic_baseline_verified_user_24);
                        BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(
                                ContextCompat.getColor(getApplicationContext(), R.color.purple_200)
                        );
                        badgeDrawable.setVisible(true);
                        badgeDrawable.setNumber(6);
                        badgeDrawable.setMaxCharacterCount(3);
                        break;
                    }
                    case 3: {
                        tab.setText("Declare");
                        tab.setIcon(R.drawable.ic_baseline_post_add_24);
                        BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(
                                ContextCompat.getColor(getApplicationContext(), R.color.purple_200)
                        );
                        badgeDrawable.setVisible(true);
                        badgeDrawable.setNumber(1);
                        badgeDrawable.setMaxCharacterCount(3);
                        break;
                    }
                }
            }
        });
        tabLayoutMediator.attach();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable = tabLayout.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(false);
            }
        });


    }


}