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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        phoneticfragment.WORD.add("발음");
        phoneticfragment.WORD.add("속담");
        phoneticfragment.WORD.add("한글");
        phoneticfragment.WORD.add("맞춤법");
        phoneticfragment.WORD.add("대학교");
        phoneticfragment.SENTENCE.add("안녕하세요");
        phoneticfragment.SENTENCE.add("반갑습니다");
        phoneticfragment.SENTENCE.add("처음뵙겠습니다");
        phoneticfragment.SENTENCE.add("감사합니다");
        phoneticfragment.SENTENCE.add("수고하세요");
        phoneticfragment.NEW.add("갑분싸");
        phoneticfragment.NEW.add("소확행");
        phoneticfragment.NEW.add("댕댕이");
        phoneticfragment.NEW.add("자만추");
        phoneticfragment.NEW.add("워라밸");

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
                print = phoneticfragment.WRONG;
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
