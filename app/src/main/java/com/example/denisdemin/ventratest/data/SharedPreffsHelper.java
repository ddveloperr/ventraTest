package com.example.denisdemin.ventratest.data;

import android.content.Context;

import com.example.denisdemin.ventratest.data.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreffsHelper {

    private Context context;
    private static String preferece ="PREFERENCE";
    private static String key_task_list = "key_task_list";

    public SharedPreffsHelper(Context context) {
        this.context = context;
    }

    public void addToTaskList(String header,String date,String comments){
        Task newTask = new Task(header, date, comments);

        Type type = new TypeToken<List<Task>>(){}.getType();

        boolean notEmpty = context.getSharedPreferences(preferece,Context.MODE_PRIVATE).contains(key_task_list);
        if(notEmpty){
            String json = context.getSharedPreferences(preferece,Context.MODE_PRIVATE).getString(key_task_list,"");
            List<Task> currentList = new Gson().fromJson(json,type);
            currentList.add(newTask);

            String newJson = new Gson().toJson(currentList,type);
            context.getSharedPreferences(preferece,Context.MODE_PRIVATE).edit().putString(key_task_list,newJson).apply();
        }else{
            List<Task> taskArrayList = new ArrayList<>();
            taskArrayList.add(newTask);
            String newJson = new Gson().toJson(taskArrayList,type);
            context.getSharedPreferences(preferece,Context.MODE_PRIVATE).edit().putString(key_task_list,newJson).apply();
        }
    }

    public List<Task> getTaskList(){
        Type type = new TypeToken<List<Task>>(){}.getType();
        String json = context.getSharedPreferences(preferece,Context.MODE_PRIVATE).getString(key_task_list,"");
        return new Gson().fromJson(json,type);
    }

    public void updateTask(String header,String date,String comment,String status,int position){
        List<Task> taskList = getTaskList();

        taskList.get(position).setHeader(header);
        taskList.get(position).setDate(date);
        taskList.get(position).setComments(comment);
        taskList.get(position).setStatus(status);

        updateList(taskList);

    }

    public void deleteTask(int position){
        List<Task> taskList = getTaskList();
        taskList.remove(position);
        updateList(taskList);
    }

    private void updateList(List<Task> list){
        Type type = new TypeToken<List<Task>>(){}.getType();
        String newJson = new Gson().toJson(list,type);
        context.getSharedPreferences(preferece,Context.MODE_PRIVATE).edit().putString(key_task_list,newJson).apply();
    }
}
