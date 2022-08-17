package com.example.shab3ni.user.homepage.userProfile.data;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentUser extends AppCompatActivity {

    private static User currentUser;

    public static void setCurrentUser(User currentUser) {
        CurrentUser.currentUser = currentUser;
    }

    public static String getFirstName() {
        return "abda";
//        return currentUser.getFirstname();
    }

    public static String getLastName() {
        return "abda";
//        return currentUser.getLastname();
    }

    public static String getEmail() {
        return "abda";
//        return currentUser.getEmail();
    }

    public static boolean isLoggedIn() {
        return true;
//        return currentUser != null;
    }
}
