package com.example.denisdemin.ventratest.mainFragment;

import android.content.Context;

import com.example.denisdemin.ventratest.data.SharedPreffsHelper;
import com.example.denisdemin.ventratest.data.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements IMainFragment.presenter {

    private final Context context;
    private IMainFragment.view mView;

    private static String errorMessage ="Please enter all fields";

    public Presenter(IMainFragment.view mView,Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void onViewCreated() {
        mView.createAddDialog();
        mView.createDateDialog();
        getCurrentTaskList();
    }

    @Override
    public void onDialogAdd(String header, String date, String comments) {
        SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
        if(header.isEmpty() || date.isEmpty() || comments.isEmpty()){
            mView.showToast(errorMessage);
        }else{
            sharedPreffsHelper.addToTaskList(header, date, comments);
            mView.hideAddDialog();
            getCurrentTaskList();
        }
    }

    @Override
    public void getCurrentTaskList() {
        SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
        List<Task> taskList;

        if (sharedPreffsHelper.getTaskList()!=null) {
            taskList = sharedPreffsHelper.getTaskList();
        }else{
            taskList = new ArrayList<>();
        }

        mView.initRecycler(taskList);
    }
}
