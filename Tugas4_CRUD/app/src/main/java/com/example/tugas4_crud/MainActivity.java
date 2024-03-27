package com.example.tugas4_crud;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tugas4_crud.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new create_fragment());

        binding.bottomBar.setOnItemSelectedListener(menuItem -> {
            int selectedItem = menuItem.getItemId();
            if (selectedItem == R.id.create){
                replaceFragment(new create_fragment());
            } else if (selectedItem == R.id.read) {
                replaceFragment(new read_fragment());
            } else if (selectedItem == R.id.update) {
                replaceFragment(new update_fragment());
            } else if (selectedItem == R.id.delete) {
                replaceFragment(new delete_fragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, fragment);
        fragmentTransaction.commit();
    }
}