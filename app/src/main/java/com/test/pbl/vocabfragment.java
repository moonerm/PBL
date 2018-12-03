package com.test.pbl;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class vocabfragment extends Fragment {

    static int pick;
    static ArrayList<String> print = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.vocabfragment, container, false);

        switch (clearlyfragment.list) {
            case (0):
                print = phoneticfragment.WORD;
                break;
            case (1):
                print = phoneticfragment.SENTENCE;
                break;
            case (2):
                print = phoneticfragment.NEW;
                break;
            case (3):
                print = phoneticfragment.HLIST;
                break;
            case (4):
                print = phoneticfragment.WRONG_P;
                break;
        }

        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, print);

        ListView listview = view.findViewById(R.id.listview);
        listview.setAdapter(Adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        pick = position;
                        break;
                    case 1:
                        pick = position;
                        break;
                    case 2:
                        pick = position;
                        break;
                    case 3:
                        pick = position;
                        break;
                    case 4:
                        pick = position;
                        break;
                }
                getFragmentManager().beginTransaction().replace(R.id.main_fragment, new phoneticfragment()).commit();
            }
        });

        return view;
    }
}
