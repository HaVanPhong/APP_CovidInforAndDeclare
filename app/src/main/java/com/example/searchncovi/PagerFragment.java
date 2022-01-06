package com.example.searchncovi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerFragment extends FragmentStateAdapter {

    public PagerFragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new RulesAndPolicyFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new AboutUsFragment();
            default:
                return new DeclareFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
