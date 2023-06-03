package edu.mirea.onebeattrue.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

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
        // Связывание BottomNavigationView с NavController
        NavigationUI.setupWithNavController(navigationView, navController);
    }
}