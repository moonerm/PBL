package com.test.pbl;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class alramfragment extends PreferenceFragment implements MainActivity.OnBackPressedListener{
    private SharedPreferences alram_Pref;
    private SharedPreferences.Editor mPrefEdit;

    homefragment mainFragment;
    Button button3;
    Switch alarmck;
    Toast testToast;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.alramfragment, container, false);
        mainFragment = new homefragment();

        alarmck = (Switch)view.findViewById(R.id.alarmck);

        alarmck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    TimePickerFragment timePickerFragment = new TimePickerFragment();
                    timePickerFragment.show(getFragmentManager(), "timePicker");
                    alarmck.setChecked(true);
                }
                else{
                }
            }
        });


        return view;
    }

//    public void showAlarmDialog(View view) {
//        this.getView();
//        TimePickerFragment timePickerFragment = new TimePickerFragment();
//        timePickerFragment.show(getFragmentManager(), "timePicker");
//    }

    @Override
    public void onBack() {
        Log.e("Other", "onBack()");
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnBackPressedListener(null);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.main_fragment, mainFragment).commit();
    }
    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        Log.e("Other", "onAttach()");
        ((MainActivity)activity).setOnBackPressedListener(this);
    }
}