package com.test.pbl;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class clearlyfragment extends Fragment {

    static int list;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        phoneticfragment.WORD.clear();
        phoneticfragment.SENTENCE.clear();
        phoneticfragment.NEW.clear();
        String[] LIST_MENU = {"단어", "문장", "신조어", "즐겨찾기", "자주 틀리는 발음"};
        View view = inflater.inflate(R.layout.clearlyfragment, container, false);
        context = container.getContext();
        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);

        ListView listview = view.findViewById(R.id.listview);
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
