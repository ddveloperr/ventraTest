package com.example.denisdemin.ventratest.mainFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.denisdemin.ventratest.R;
import com.example.denisdemin.ventratest.data.model.Task;
import com.example.denisdemin.ventratest.detailFragment.DetailFragment;
import com.example.denisdemin.ventratest.mainActivity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<Task> taskList;

    public MainRecyclerAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_container)
        RelativeLayout itemContainer;

        @BindView(R.id.textView_item_header)
        TextView textViewHeader;

        @BindView(R.id.textView_item_date)
        TextView textViewDate;

        @BindView(R.id.textView_item_comment)
        TextView textViewComment;

        @BindView(R.id.textView_task_status)
        TextView textViewTaskStatus;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_recycler_main,parent,false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainViewHolder myHolder = (MainViewHolder) holder;





        final String header = taskList.get(position).getHeader();
        final String date = taskList.get(position).getDate();
        final String comment = taskList.get(position).getComments();
        final String status = taskList.get(position).getStatus();
        final int positionBundle = position;

        myHolder.textViewHeader.setText(header);
        myHolder.textViewDate.setText(date);
        myHolder.textViewComment.setText(comment);
        myHolder.textViewTaskStatus.setText(status);
        myHolder.textViewTaskStatus.setTextColor(getStatusColor(status));

        myHolder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("header",header);
                bundle.putString("date",date);
                bundle.putString("comment",comment);
                bundle.putString("status",status);
                bundle.putInt("position",positionBundle);
                ((MainActivity)context).setFragmentBackStack(new DetailFragment(),bundle);
            }
        });

    }

    private int getStatusColor(String status){
        int color;
        switch (status){
            case Task.statusNew:
                color =  R.color.colorNew;
                break;
            case Task.statusPending:
                color = R.color.colorPending;
                break;
            case Task.statusDone:
                color = R.color.colorDone;
                break;
                default:
                    color = R.color.colorPrimaryDark;
                    break;
        }
        return color;
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
