package com.example.shab3ni.user.homepage.menu.category;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.shab3ni.user.homepage.menu.data.CategoryModel;

import java.util.ArrayList;

public class categoryTabLayoutAdapter extends FragmentStatePagerAdapter {
    private final Context context;
    int totalTabs;

    public ArrayList<CategoryModel> allCategory;

    public categoryTabLayoutAdapter(FragmentManager fm, Context context, int totalTabs, ArrayList<CategoryModel> allCategory) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
        this.allCategory = allCategory;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new CategoryRecyclerViewFragment(allCategory.get(position));
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
