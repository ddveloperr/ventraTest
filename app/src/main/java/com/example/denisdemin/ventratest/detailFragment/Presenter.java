package com.example.denisdemin.ventratest.detailFragment;

import android.content.Context;

import com.example.denisdemin.ventratest.data.SharedPreffsHelper;
import com.example.denisdemin.ventratest.mainActivity.MainActivity;

public class Presenter implements IDetailFragment.presenter {

    private IDetailFragment.view mView;

    private Context context;

    private static String errorMessage ="Please enter all fields";

    private static String updatedMessage="Updated";

    public Presenter(IDetailFragment.view mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void onViewCreated() {
        mView.initView();
        mView.initAlertDialog();
        mView.createDateDialog();
        mView.initSpinner();
    }

    @Override
    public void updateTask(String header, String date, String comment, String status, int position) {
        if (!header.isEmpty() && !date.isEmpty() && !comment.isEmpty() && !status.isEmpty()) {
            SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
            sharedPreffsHelper.updateTask(header, date, comment, status, position);
            mView.showToast(updatedMessage);
        }else{
            mView.showToast(errorMessage);
        }
    }

    @Override
    public void deleteTask(int position) {
        SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
        sharedPreffsHelper.deleteTask(position);
        ((MainActivity)context).popBackStack();
    }
}
