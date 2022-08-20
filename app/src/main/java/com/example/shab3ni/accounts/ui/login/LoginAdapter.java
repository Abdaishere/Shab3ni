package com.example.shab3ni.accounts.ui.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shab3ni.accounts.ui.register.SignupTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    private final Context context;
    final int totalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LoginTabFragment();
        }
        return new SignupTabFragment();
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
