package com.example.shab3ni.accounts.ui.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.shab3ni.accounts.ui.register.SignupTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    private final Context context;
    int totalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                System.out.println("login");
                return new LoginTabFragment();
            case 1:
                System.out.println("signup");
                return new SignupTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
