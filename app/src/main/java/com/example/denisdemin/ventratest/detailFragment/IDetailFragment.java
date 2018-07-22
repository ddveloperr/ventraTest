package com.example.denisdemin.ventratest.detailFragment;

public interface IDetailFragment {
    interface view{
        void initView();
        void showToast(String message);
        void initAlertDialog();
        void initSpinner();
        void showAlertDialog();
        void hideAlertDialog();
        void createDateDialog();
        void showDateDialog();
        void hideDateDialog();
    }
    interface presenter{
        void onViewCreated();
        void updateTask(String header,String date,String comment,String status,int position);
        void deleteTask(int position);
    }
}
