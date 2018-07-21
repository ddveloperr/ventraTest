package com.example.denisdemin.ventratest.mainFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denisdemin.ventratest.R;
import com.example.denisdemin.ventratest.data.model.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements IMainFragment.view, View.OnClickListener,DatePickerDialog.OnDateSetListener {

    @BindView(R.id.recyclerMain)
    RecyclerView recyclerView;

    @BindView(R.id.fabMain)
    FloatingActionButton floatingActionButton;

    EditText editTextHeader;

    TextView textViewDate;

    EditText editTextComments;

    Button addButton;

    AlertDialog dialog;

    DatePickerDialog datePickerDialog;

    private Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        mPresenter = new Presenter(this,getContext());

        floatingActionButton.setOnClickListener(this);

        mPresenter.onViewCreated();

    }

    @Override
    public void createAddDialog(){
        dialog = new AlertDialog.Builder(getContext()).create();
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_view,null);

        editTextHeader = dialogView.findViewById(R.id.editText_Header);
        textViewDate = dialogView.findViewById(R.id.textView_date);
        editTextComments = dialogView.findViewById(R.id.editText_Comment);
        addButton = dialogView.findViewById(R.id.button_add);

        dialog.setView(dialogView);

        textViewDate.setOnClickListener(this);
        addButton.setOnClickListener(this);
    }

    @Override
    public void showAddDialog(){
        dialog.show();
    }

    @Override
    public void hideAddDialog(){
        dialog.dismiss();
    }

    @Override
    public void createDateDialog(){
        datePickerDialog = new DatePickerDialog(getContext(),this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void showDateDialog(){
        datePickerDialog.show();
    }

    @Override
    public void hideDateDialog(){
        datePickerDialog.hide();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView_date:
                showDateDialog();
                break;
            case R.id.button_add:
                mPresenter.onDialogAdd(editTextHeader.getText().toString(),textViewDate.getText().toString(),editTextComments.getText().toString());
                break;
            case R.id.fabMain:
                showAddDialog();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month++;
        textViewDate.setText(new StringBuilder().append(day).append(".").append(month).append(".").append(year).toString());
        hideDateDialog();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initRecycler(List<Task> list) {
        recyclerView.setAdapter(new MainRecyclerAdapter(getContext(),list));
    }

    @Override
    public void updateRecycler(List<Task> list) {

    }
}
