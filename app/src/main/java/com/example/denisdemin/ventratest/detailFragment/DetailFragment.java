package com.example.denisdemin.ventratest.detailFragment;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denisdemin.ventratest.R;
import com.example.denisdemin.ventratest.data.model.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements IDetailFragment.view,DialogInterface.OnClickListener {

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

        initView();
        initDialog();
        initSpinner();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.updateTask(editTextHeader.getText().toString(),
                        textViewDate.getText().toString(),
                        editTextComment.getText().toString(),
                        spinnerStatus.getSelectedItem().toString(),
                        getArguments().getInt("position"));
            }
        });
    }

    @Override
    public void initView() {
        final Bundle bundle = getArguments();
        editTextHeader.setText(bundle.getString("header"));
        textViewDate.setText(bundle.getString("date"));
        editTextComment.setText(bundle.getString("comment"));

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initDialog() {
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
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.hide();
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case DialogInterface.BUTTON_POSITIVE:
                mPresenter.deleteTask(getArguments().getInt("position"));
                break;
        }
        hideDialog();
    }
}
