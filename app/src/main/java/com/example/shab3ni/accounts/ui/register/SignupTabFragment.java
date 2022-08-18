package com.example.shab3ni.accounts.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.shab3ni.R;
import com.example.shab3ni.accounts.api.AccountsControllerKt;
import com.example.shab3ni.accounts.ui.login.LoginActivity;

public class SignupTabFragment extends Fragment {
    final int Duration = 600;
    private EditText name;
    private EditText email;
    private EditText pass;
    private EditText conf_pass;
    private Button signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.register_tab_fragment, container, false);

        name = root.findViewById(R.id.name);
        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.password);
        conf_pass = root.findViewById(R.id.password_conform);
        signup = root.findViewById(R.id.btn_signup);
        animation(root);

        signup.setOnClickListener(v -> {
            int errors = 0;
            String error = null;
            String[] nameString = name.getText().toString().split(" ");

            if (nameString[0].isEmpty()) {
                errors++;
                error = "First Name is required!";
            }
            if (nameString[1].isEmpty()) {
                errors++;
                error = "Second Name is required!";
            }
            if (email.getText().toString().isEmpty()) {
                errors++;
                error = "Email is required!";

            }
            if (pass.getText().toString().isEmpty()) {
                errors++;
                error = "Password is required!";
            }
            if (conf_pass.getText().toString().isEmpty()) {
                errors++;
                error = "Conform Password is required!";
            }
            if (pass.getText().toString().equals(conf_pass.getText().toString())) {
                errors++;
                error = "Passwords don't match";
            }

            if (errors == 0) {

                AccountsControllerKt.register(requireContext(),
                        nameString[0],
                        nameString[1],
                        email.getText().toString(),
                        pass.getText().toString(),
                        conf_pass.getText().toString());

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            } else if (errors == 1) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getContext(), "Error: Please try again", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        return root;
    }


    public void animation(ViewGroup root) {
        name.setTranslationX(800);
        email.setTranslationX(800);
        pass.setTranslationX(800);
        conf_pass.setTranslationX(800);
        signup.setTranslationX(800);

        name.setAlpha((float) 0);
        email.setAlpha((float) 0);
        pass.setAlpha((float) 0);
        conf_pass.setAlpha((float) 0);
        signup.setAlpha((float) 0);

        name.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(500).start();
        email.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(600).start();
        pass.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(800).start();
        conf_pass.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(1000).start();
        signup.animate().translationX(0).alpha(1).setDuration(Duration).setStartDelay(1100).start();
    }
}
