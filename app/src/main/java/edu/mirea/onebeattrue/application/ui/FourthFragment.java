package edu.mirea.onebeattrue.application.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.mirea.onebeattrue.application.databinding.FragmentFourthBinding;

public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFourthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}