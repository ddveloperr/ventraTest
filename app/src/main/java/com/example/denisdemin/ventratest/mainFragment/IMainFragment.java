package com.example.denisdemin.ventratest.mainFragment;

import android.os.Bundle;

import com.example.denisdemin.ventratest.data.model.Task;

import java.util.List;

public interface IMainFragment {
    interface view{
        void createAddDialog();
        void showAddDialog(String message);
        void hideAddDialog();
        void createDateDialog();
        void showDateDialog();
        void hideDateDialog();
        void showToast(String message);
        void initRecycler(List<Task> list);
        void updateRecycler(Task task);
    }

    interface presenter{
        void onViewCreated(Bundle bundle);
        void onDialogAdd(String header,String date,String comments);
        List<Task> getCurrentTaskList();
        void displayData(List<Task> taskList);
        List<Task> sortList(List<Task> taskList);
        void onSortButtonClicked();
    }
}
