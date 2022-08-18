package com.example.shab3ni.user.homepage.userProfile.data;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shab3ni.accounts.api.AccountsControllerKt;

public class CurrentUser extends AppCompatActivity {

    private static User currentUser;
    private static String Token;

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
//        try {
//            AccountsControllerKt.getUserDetails(Token);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
        return Token != null;
    }

    public static String getToken() {
        return Token;
    }

    public static void setToken(String token) {
        Token = token;
    }
}
