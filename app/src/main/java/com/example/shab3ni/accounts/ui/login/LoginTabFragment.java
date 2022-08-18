package com.example.shab3ni.accounts.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.shab3ni.MainActivity;
import com.example.shab3ni.R;
import com.example.shab3ni.accounts.api.AccountsControllerKt;
import com.example.shab3ni.user.homepage.userProfile.data.CurrentUser;

public class LoginTabFragment extends Fragment {

    final int Duration = 400;
    EditText email;
    EditText pass;
    TextView forget_pass;
    Button Login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.password);
        forget_pass = root.findViewById(R.id.forget_password);
        Login = root.findViewById(R.id.btn_login);

        Login.setOnClickListener(v -> {
            int errors = 0;
            String error = null;
            if (email.getText().toString().isEmpty()) {
                errors++;
                error = "Email is required!";

            }
            if (pass.getText().toString().isEmpty()) {
                errors++;
                error = "Password is required!";
            }

            if (errors == 0) {

                AccountsControllerKt.login(requireContext(),
                        email.getText().toString(),
                        pass.getText().toString());

                if (CurrentUser.getToken() != null) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }

            } else if (errors == 1) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getContext(), "Error: Please try again", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        animation(root);

        return root;
    }

    public void animation(ViewGroup root) {


        email.setTranslationX(800);
        pass.setTranslationX(800);
        forget_pass.setTranslationX(800);
        Login.setTranslationX(800);

        email.setAlpha((float) 0);
        pass.setAlpha((float) 0);
        forget_pass.setAlpha((float) 0);
        Login.setAlpha((float) 0);

        email.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(500).start();
        forget_pass.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(500).start();
        Login.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(700).start();

    }


}
