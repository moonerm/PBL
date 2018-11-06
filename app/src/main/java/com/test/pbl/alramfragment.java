package com.test.pbl;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

public class alramfragment extends Fragment implements MainActivity.OnBackPressedListener{
    homefragment mainFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alramfragment, container, false);
        mainFragment = new homefragment();
        return view;

    }

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