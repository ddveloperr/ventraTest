package com.example.denisdemin.ventratest.mainFragment;

import android.content.Context;
import android.os.Bundle;

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
    public void onViewCreated(Bundle bundle) {
        SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
        mView.createAddDialog();
        mView.createDateDialog();


        if(sharedPreffsHelper.getSortedList()){
            displayData(sortList(getCurrentTaskList()));
        }else{
            displayData(getCurrentTaskList());
        }

        if(bundle.getBoolean("intent")){
            mView.showAddDialog(bundle.getString("text"));
        }
    }

    @Override
    public void onDialogAdd(String header, String date, String comments) {
        SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
        if (!header.isEmpty() && !date.isEmpty() && !comments.isEmpty()) {
            Task task = new Task(header, date, comments);
            sharedPreffsHelper.addToTaskList(task);
            mView.hideAddDialog();

            mView.updateRecycler(task);
        } else {
            mView.showToast(errorMessage);
        }
    }

    @Override
    public List<Task> getCurrentTaskList() {
        SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
        List<Task> taskList;

        if (sharedPreffsHelper.getTaskList()!=null) {
            taskList = sharedPreffsHelper.getTaskList();
        }else{
            taskList = new ArrayList<>();
        }

        return taskList;
    }

    @Override
    public void displayData(List<Task> taskList) {
        mView.initRecycler(taskList);
    }

    @Override
    public List<Task> sortList(List<Task> taskList) {
        List<Task> newList = new ArrayList<>();

        for(int i=0;i<taskList.size();i++){
            if(taskList.get(i).getStatus().equals(Task.statusNew)){
                newList.add(taskList.get(i));
            }
        }
        for(int i=0;i<taskList.size();i++){
            if(taskList.get(i).getStatus().equals(Task.statusPending)){
                newList.add(taskList.get(i));
            }
        }
        for(int i=0;i<taskList.size();i++){
            if(taskList.get(i).getStatus().equals(Task.statusDone)){
                newList.add(taskList.get(i));
            }
        }
        return newList;
    }

    @Override
    public void onSortButtonClicked() {
        SharedPreffsHelper sharedPreffsHelper = new SharedPreffsHelper(context);
        if(sharedPreffsHelper.getSortedList()){
            sharedPreffsHelper.setReturnSortedList(false);
            displayData(getCurrentTaskList());
        }else{
            sharedPreffsHelper.setReturnSortedList(true);
            displayData(sortList(getCurrentTaskList()));
        }
    }
}
