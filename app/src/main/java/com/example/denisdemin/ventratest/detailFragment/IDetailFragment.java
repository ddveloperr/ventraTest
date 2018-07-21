package com.example.denisdemin.ventratest.detailFragment;

public interface IDetailFragment {
    interface view{
        void initView();
        void showToast(String message);
        void initDialog();
        void initSpinner();
        void showDialog();
        void hideDialog();
    }
    interface presenter{
        void updateTask(String header,String date,String comment,String status,int position);
        void deleteTask(int position);
    }
}
