package com.test.pbl;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class clearlyfragment extends Fragment {

    static int list;
    Context context;
    static final ArrayList<String> searchText = new ArrayList<>();
    ArrayList<String> LIST_MENU = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clearlyfragment, container, false);
        if(LIST_MENU.isEmpty()) {
            LIST_MENU.add("단어");
            LIST_MENU.add("문장");
            LIST_MENU.add("신조어");
            LIST_MENU.add("즐겨찾기");
            LIST_MENU.add("자주 틀리는 발음");
        }

        if(phoneticfragment.WORD.isEmpty() && phoneticfragment.SENTENCE.isEmpty() && phoneticfragment.NEW.isEmpty()) {
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
        }
        context = container.getContext();

        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);
        final EditText editText = (EditText)view.findViewById(R.id.edt_search);
        ListView listview = view.findViewById(R.id.listview);
        Button button = view.findViewById(R.id.btn_search);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length()==0){
                    Toast.makeText(context, "검색할 발음을 입력해주세요.", Toast.LENGTH_LONG).show();
                }else {
                    if(phoneticfragment.WORD.contains(editText.getText().toString())){
                        list=0;
                        searchText.add(editText.getText().toString());
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, new vocabfragment()).commit();
                    }else if(phoneticfragment.SENTENCE.contains(editText.getText().toString())){
                        list=1;
                        searchText.add(editText.getText().toString());
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, new vocabfragment()).commit();
                    }else if(phoneticfragment.NEW.contains(editText.getText().toString())){
                        list=2;
                        searchText.add(editText.getText().toString());
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, new vocabfragment()).commit();
                    }else Toast.makeText(context, "검색한 발음이 없습니다!", Toast.LENGTH_LONG).show();
                }
            }
        });

        listview.setAdapter(Adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            list = position;
                            getFragmentManager().beginTransaction().replace(R.id.main_fragment, new vocabfragment()).commit();
                            break;
                        case 1:
                            list = position;
                            getFragmentManager().beginTransaction().replace(R.id.main_fragment, new vocabfragment()).commit();
                            break;
                        case 2:
                            list = position;
                            getFragmentManager().beginTransaction().replace(R.id.main_fragment, new vocabfragment()).commit();
                            break;
                        case 3:
                            list = position;
                            if(phoneticfragment.HLIST.isEmpty()){
                                Toast.makeText(context, "아직 즐겨찾기가 없습니다.", Toast.LENGTH_LONG).show();
                            }else{
                                getFragmentManager().beginTransaction().replace(R.id.main_fragment, new vocabfragment()).commit();
                            }
                            break;
                        case 4:
                            list = position;
                            if(phoneticfragment.WRONG_P.isEmpty()){
                                Toast.makeText(context, "아직 자주 틀리는 발음이 없습니다.", Toast.LENGTH_LONG).show();
                            }else{
                                getFragmentManager().beginTransaction().replace(R.id.main_fragment, new vocabfragment()).commit();
                            }
                            break;
                    }
                }
        });
        return view;
    }

}
