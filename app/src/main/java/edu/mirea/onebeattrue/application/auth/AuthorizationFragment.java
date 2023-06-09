package edu.mirea.onebeattrue.application.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.mirea.onebeattrue.application.MainActivity;
import edu.mirea.onebeattrue.application.R;
import edu.mirea.onebeattrue.application.databinding.FragmentAuthorizationBinding;

public class AuthorizationFragment extends Fragment {

    private FragmentAuthorizationBinding binding;

    private FirebaseAuth auth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Navigation.findNavController(binding.getRoot())
                    .navigate(R.id.action_authorizationFragment_to_firstFragment);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false);

        // переход на экран регистрации

        binding.clickToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_authorizationFragment_to_registrationFragment);
            }
        });

        // авторизация пользователя

        auth = FirebaseAuth.getInstance();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(binding.emailField.getText());
                password = String.valueOf(binding.passwordField.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getContext(), "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getContext(), "Invalid email format", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8) {
                    Toast.makeText(getContext(), "Password should be at least 8 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                    Navigation.findNavController(binding.getRoot())
                                            .navigate(R.id.action_authorizationFragment_to_firstFragment);
                                } else {
                                    Toast.makeText(getContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        return binding.getRoot();
    }
}