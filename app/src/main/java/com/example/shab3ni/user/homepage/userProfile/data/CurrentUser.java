package com.example.shab3ni.user.homepage.userProfile.data;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentUser extends AppCompatActivity {

    private static User currentUser;

    public static void setCurrentUser(User currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static String getFirstName() {
        return currentUser.getFirstname();
    }

    public static String getLastName() {
        return currentUser.getLastname();
    }

    public static String getEmail() {
        return currentUser.getEmail();
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
