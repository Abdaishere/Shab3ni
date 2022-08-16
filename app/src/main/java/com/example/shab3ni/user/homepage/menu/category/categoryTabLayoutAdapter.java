package com.example.shab3ni.user.homepage.menu.category;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class categoryTabLayoutAdapter extends FragmentPagerAdapter {
    private final Context context;
    int totalTabs;

    /*
                0 -> "Main Meal"
                1 -> "Sides"
                2 -> "Snacks"
                3 -> "Dessert"

         */
    public categoryTabLayoutAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new CategoryRecyclerViewFragment(position);
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
