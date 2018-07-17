package com.example.denisdemin.ventratest.mainFragment;

public interface IMainFragment {
    interface view{
        void createAddDialog();
        void showAddDialog();
        void hideAddDialog();
        void createDateDialog();
        void showDateDialog();
        void hideDateDialog();
        void showToast(String message);
    }

    interface presenter{
        void onDialogAdd(String header,String date,String comments);
    }
}
