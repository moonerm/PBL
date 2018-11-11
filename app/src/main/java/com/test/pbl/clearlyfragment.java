package com.test.pbl;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class clearlyfragment extends Fragment {
    static final String[] LIST_MENU = {"단어", "문장", "신조어", "즐겨찾기", "자주 틀리는 문장"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.clearlyfragment, null);
        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);

        ListView listview = view.findViewById(R.id.listview1);
        listview.setAdapter(Adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                adapterView.getItemAtPosition(position);
                switch (position) {
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, new phoneticfragment()).commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, new phoneticfragment()).commit();
                        break;
                    case 2:
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, new phoneticfragment()).commit();
                        break;
                    case 3:
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, new phoneticfragment()).commit();
                        break;
                    case 4:
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, new phoneticfragment()).commit();
                        break;
                }
            }
        });
        return view;
    }
}
