package com.example.denisdemin.ventratest.mainActivity;

import android.content.Intent;
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


        setFragment(new MainFragment(),setBundle(getTextIntent()));
    }

    private String getTextIntent(){
        Intent intent = getIntent();

        return intent.getStringExtra(Intent.EXTRA_TEXT);
    }

    private Bundle setBundle(String textIntent){
        Bundle bundle = new Bundle();

        if(textIntent!=null){
            bundle.putBoolean("intent",true);
            bundle.putString("text",textIntent);
        }else{
            bundle.putBoolean("intent",false);
        }
        return bundle;
    }

    public void setFragment(@NonNull Fragment fragment,@Nullable Bundle args){
        fragment.setArguments(args);

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
