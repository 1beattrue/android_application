package edu.mirea.onebeattrue.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.mirea.onebeattrue.application.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navigationView = binding.bottomNavigationView;
        NavController navController = Navigation.findNavController(this, R.id.navigation_host_fragment);
        // связывание BottomNavigationView с NavController
        NavigationUI.setupWithNavController(navigationView, navController);

        // удаление bottom_navigation_view
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.authorizationFragment || navDestination.getId() == R.id.registrationFragment) {
                    binding.bottomNavigationView.setVisibility(View.GONE);
                }
                else {
                    binding.bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}