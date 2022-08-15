package com.example.shab3ni.accounts.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.shab3ni.R;

public class LoginTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        EditText email = root.findViewById(R.id.email);
        EditText pass = root.findViewById(R.id.password);
        TextView forget_pass = root.findViewById(R.id.forget_password);
        Button Login = root.findViewById(R.id.btn_login);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        forget_pass.setTranslationX(800);
        Login.setTranslationX(800);

        email.setAlpha((float) 0);
        pass.setAlpha((float) 0);
        forget_pass.setAlpha((float) 0);
        Login.setAlpha((float) 0);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forget_pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        Login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        return root;
    }
}
