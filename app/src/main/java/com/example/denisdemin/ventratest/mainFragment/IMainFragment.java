package com.example.denisdemin.ventratest.mainFragment;

import com.example.denisdemin.ventratest.data.model.Task;

import java.util.List;

public interface IMainFragment {
    interface view{
        void createAddDialog();
        void showAddDialog();
        void hideAddDialog();
        void createDateDialog();
        void showDateDialog();
        void hideDateDialog();
        void showToast(String message);
        void initRecycler(List<Task> list);
        void updateRecycler(List<Task> list);
    }

    interface presenter{
        void onViewCreated();
        void onDialogAdd(String header,String date,String comments);
        void getCurrentTaskList();
    }
}
