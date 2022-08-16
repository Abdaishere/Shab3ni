package com.example.shab3ni.user.homepage.userProfile.data;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shab3ni.accounts.ui.login.LoginActivity;

public class CurrentUser extends AppCompatActivity {

    private static User currentUser = new User();

    private static String token = "";

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        CurrentUser.token = token;
    }


}
