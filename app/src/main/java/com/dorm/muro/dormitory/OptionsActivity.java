package com.dorm.muro.dormitory;

import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsActivity extends AppCompatActivity implements DatePickerFragment.DialogResultListener {
    @BindView(R.id.et_settings_contract)
    EditText mContractInfo;

    @BindView(R.id.et_settings_fio)
    EditText mFIO;

    @BindView(R.id.et_settings_cost_per_month)
    EditText mCostPerMonth;

    @BindView(R.id.bn_settings_select_date_from)
    Button mMonthsToPayFrom;

    @BindView(R.id.bn_settings_select_date_to)
    Button mMonthsToPayTo;

    private SharedPreferences preferences;
    private Toast savedToast;
    public static final String FROM = "FROM";
    public static final String TO = "TO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ButterKnife.bind(this);

        preferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES, MODE_PRIVATE);

        mContractInfo.setText(preferences.getString(PaymentFragment.CONTRACT_ID, ""));
        mFIO.setText(preferences.getString(PaymentFragment.USER_FIO, ""));
        mCostPerMonth.setText(String.valueOf(preferences.getFloat(PaymentFragment.MONTHLY_COST, 0)));
        mMonthsToPayFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                Bundle args = new Bundle(1);
                args.putString(MainActivity.DIALOG_TAG, FROM);
                datePicker.setArguments(args);
                datePicker.show(getSupportFragmentManager(), "DatePickerFragment");
            }
        });

        mMonthsToPayTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                Bundle args = new Bundle(1);
                args.putString(MainActivity.DIALOG_TAG, TO);
                datePicker.setArguments(args);
                datePicker.show(getSupportFragmentManager(), "DatePickerFragment");
            }
        });
        savedToast = Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        preferences.edit()
                .putString(PaymentFragment.CONTRACT_ID, mContractInfo.getText().toString())
                .putString(PaymentFragment.USER_FIO, mFIO.getText().toString())
                .putFloat(PaymentFragment.MONTHLY_COST, Float.parseFloat(mCostPerMonth.getText().toString()))
                .putString(PaymentFragment.MONTHS_TO, mMonthsToPayTo.getText().toString())
                .putString(PaymentFragment.MONTHS_FROM, mMonthsToPayFrom.getText().toString())
                .apply();
        savedToast.show();
    }

    @Override
    public void onDateSelected(int year, int month, String dialogTag) {
        switch (dialogTag){
            case FROM:{
                mMonthsToPayFrom.setText(month + "/" + year);
                break;
            }
            case TO:{
                mMonthsToPayTo.setText(month + "/" + year);
                break;
            }
        }
    }
}