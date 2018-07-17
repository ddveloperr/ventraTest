package com.example.denisdemin.ventratest.mainFragment;

import android.content.Context;

import com.example.denisdemin.ventratest.data.SharedPreffsHelper;

public class Presenter implements IMainFragment.presenter {

    private final Context context;
    private IMainFragment.view mView;

    private static String errorMessage ="Please enter all fields";

    public Presenter(IMainFragment.view mView,Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void onDialogAdd(String header, String date, String comments) {
        SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
        if(header.isEmpty() || date.isEmpty() || comments.isEmpty()){
            mView.showToast(errorMessage);
        }else{
            sharedPreffsHelper.addToTaskList(header, date, comments);
            mView.hideAddDialog();
        }
    }
}
