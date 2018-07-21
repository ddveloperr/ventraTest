package com.example.denisdemin.ventratest.mainActivity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.denisdemin.ventratest.R;
import com.example.denisdemin.ventratest.mainFragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new MainFragment());
    }

    public void setFragment(@NonNull Fragment fragment){

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.addToBackStack(fragment.getTag()).replace(R.id.frameContainer,fragment)
                .commit();
    }

    public void setFragmentBackStack(@NonNull Fragment fragment,@Nullable Bundle args){
        fragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.addToBackStack(fragment.getTag()).replace(R.id.frameContainer,fragment)
                .commit();
    }

    public void popBackStack(){
        getSupportFragmentManager().popBackStackImmediate();
    }
}
