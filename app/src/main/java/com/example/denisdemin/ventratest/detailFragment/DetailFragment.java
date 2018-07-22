package com.example.denisdemin.ventratest.detailFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denisdemin.ventratest.R;
import com.example.denisdemin.ventratest.data.model.Task;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements IDetailFragment.view,DialogInterface.OnClickListener,View.OnClickListener,DatePickerDialog.OnDateSetListener {

    @BindView(R.id.editText_Header)
    EditText editTextHeader;

    @BindView(R.id.button_delete)
    ImageButton buttonDelete;

    @BindView(R.id.spinner_status)
    AppCompatSpinner spinnerStatus;

    @BindView(R.id.textView_date)
    TextView textViewDate;

    @BindView(R.id.editText_Comment)
    EditText editTextComment;

    @BindView(R.id.saveButton)
    Button saveButton;

    private Presenter mPresenter;

    AlertDialog dialog;

    private static final String promptMessage="Are you sure?";
    private DatePickerDialog datePickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new Presenter(this,getContext());

        ButterKnife.bind(this,view);

        mPresenter.onViewCreated();
    }

    @Override
    public void initView() {
        final Bundle bundle = getArguments();
        editTextHeader.setText(bundle.getString("header"));
        textViewDate.setText(bundle.getString("date"));
        editTextComment.setText(bundle.getString("comment"));

        buttonDelete.setOnClickListener(this);
        textViewDate.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initAlertDialog() {
        dialog = new AlertDialog.Builder(getContext())
                .setMessage(promptMessage)
                .setPositiveButton("Yes",this)
                .setNegativeButton("No",this)
                .create();
    }

    @Override
    public void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, Task.statusList);
        spinnerStatus.setAdapter(adapter);
        spinnerStatus.setSelection(selectCurrentSpinnerItem(getArguments().getString("status")));
    }

    private int selectCurrentSpinnerItem(String status){
        int temp=0;
        for(int i=0;i<Task.statusList.length;i++){
            if(status.equals(Task.statusList[i])){
                temp=i;
                break;
            }
        }
        return temp;
    }

    @Override
    public void showAlertDialog() {
        dialog.show();
    }

    @Override
    public void hideAlertDialog() {
        dialog.hide();
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
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case DialogInterface.BUTTON_POSITIVE:
                mPresenter.deleteTask(getArguments().getInt("position"));
                break;
        }
        hideAlertDialog();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView_date:
                showDateDialog();
                break;
            case R.id.button_delete:
                showAlertDialog();
                break;
            case R.id.saveButton:
                mPresenter.updateTask(editTextHeader.getText().toString(),
                        textViewDate.getText().toString(),
                        editTextComment.getText().toString(),
                        spinnerStatus.getSelectedItem().toString(),
                        getArguments().getInt("position"));
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month++;
        textViewDate.setText(new StringBuilder().append(day).append(".").append(month).append(".").append(year).toString());
        hideDateDialog();
    }
}
