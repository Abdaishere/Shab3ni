package com.example.shab3ni.accounts.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.shab3ni.R;

public class SignupTabFragment extends Fragment {
    final int Duration = 600;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.register_tab_fragment, container, false);
        animation(root);


        return root;
    }

    public void animation(ViewGroup root){
        EditText name = root.findViewById(R.id.name);
        EditText email = root.findViewById(R.id.email);
        EditText pass = root.findViewById(R.id.password);
        EditText conf_pass = root.findViewById(R.id.password_conform);
        Button Login = root.findViewById(R.id.btn_signup);

        name.setTranslationX(800);
        email.setTranslationX(800);
        pass.setTranslationX(800);
        conf_pass.setTranslationX(800);
        Login.setTranslationX(800);

        name.setAlpha((float) 0);
        email.setAlpha((float) 0);
        pass.setAlpha((float) 0);
        conf_pass.setAlpha((float) 0);
        Login.setAlpha((float) 0);

        name.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(500).start();
        email.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(600).start();
        pass.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(800).start();
        conf_pass.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(1000).start();
        Login.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(1100).start();
    }
}
