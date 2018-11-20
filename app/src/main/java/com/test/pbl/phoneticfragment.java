package com.test.pbl;
import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class phoneticfragment extends Fragment {
    Intent intent;
    SpeechRecognizer mRecognizer;
    TextView stext, ptext; // stext : 사용자의 음성 ptext : 연습할 글
    ImageButton button;
    Button pre, next, Hbutton;
    int index = vocabfragment.pick;// 배열위치
    private Context context; //토스트메세지를 위한 Context

    static final ArrayList<String> WORD = new ArrayList<>(); //단어
    static final String[] WORD_S = {"[바름]", "[속땀]","[한:글]", "[맏춤뻡]", "[대:학꾜]"}; // 단어 발음소리
    static final ArrayList<String> SENTENCE = new ArrayList<>();//문장
    static final ArrayList<String> NEW = new ArrayList<>(); //신조어
    static final ArrayList<String> HLIST = new ArrayList<>(); //즐겨찾기
    static final ArrayList<String> WRONG = new ArrayList<>(); //자주틀리는발음


    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState){//프래그먼트가 생성될 때 호출되는 부분
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {//onCreate 후 화면을 구성할 때 호출되는 부분

        View view = inflater.inflate(R.layout.phoneticfragment,container,false);
        super.onCreateView(inflater, container, savedInstanceState);
        context = container.getContext();
        //setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)//this를 다 getActivity로 바꿔본다
                //getActivity가 context라는 블로거의 말을 믿는다
                != PackageManager.PERMISSION_GRANTED)  {
            if
                    (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.RECORD_AUDIO)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO
                );
            }
        }

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getActivity().getPackageName());//호출한 패키지
        //getPackageName()은 왜 에러가 나냐하면 액티비티에서 사용하는 메소드라 프래그먼트에서 바로 쓸 수가 없음
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        mRecognizer.setRecognitionListener(recognitionListener);

        pre = (Button)view.findViewById(R.id.pre);
        next = (Button)view.findViewById(R.id.next);
        Hbutton = (Button)view.findViewById(R.id.hbutton);
        if(HLIST.contains(vocabfragment.print.get(index).toString())){
            Hbutton.setText("\u2605");
        }else {
            Hbutton.setText("\u2606");
        }

        pre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(index==0) index=vocabfragment.print.size()-1;
                else index--;
                ptext.setText(vocabfragment.print.get(index).toString());
                if(vocabfragment.print.get(index).toString().equals(WORD.get(index).toString())){
                    ptext.setText(vocabfragment.print.get(index).toString()+WORD_S[index]); // 단어를 선택했을 시 발음소리도 같이 출력
                }
                if(HLIST.contains(vocabfragment.print.get(index).toString())){
                    Hbutton.setText("\u2605");
                }else {
                    Hbutton.setText("\u2606");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(index>=vocabfragment.print.size()-1) index=0;
                else index++;
                ptext.setText(vocabfragment.print.get(index).toString());
                if(vocabfragment.print.get(index).toString().equals(WORD.get(index).toString())){
                    ptext.setText(vocabfragment.print.get(index).toString()+WORD_S[index]); // 단어를 선택했을 시 발음소리도 같이 출력
                }
                if(HLIST.contains(vocabfragment.print.get(index).toString())){
                    Hbutton.setText("\u2605");
                }else {
                    Hbutton.setText("\u2606");
                }
            }
        });

        Hbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(HLIST.contains(vocabfragment.print.get(index).toString())){
                    HLIST.remove(vocabfragment.print.get(index).toString());

                    Hbutton.setText("\u2606");
                }else {
                    HLIST.add(vocabfragment.print.get(index).toString());
                    Hbutton.setText("\u2605");
                }
                if(clearlyfragment.list==3 && phoneticfragment.HLIST.isEmpty()){ //즐겨찾기를 다 취소했을 경우
                    Toast.makeText(context, "더이상 즐겨찾기가 없습니다.", Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().replace(R.id.main_fragment, new clearlyfragment()).commit();
                }
            }
        });

        stext = (TextView)view.findViewById(R.id.speechText);//fVBI를 fragment로 바꿀때: getView()를 앞에 붙인다
        ptext = (TextView)view.findViewById(R.id.practiceText);
        ptext.setText(vocabfragment.print.get(index).toString());
        if(vocabfragment.print.get(index).toString().equals(WORD.get(index).toString())){
            ptext.setText(vocabfragment.print.get(index).toString()+WORD_S[index]); // 단어를 선택했을 시 발음소리도 같이 출력
        }


        button = (ImageButton)view.findViewById(R.id.button1);//view.으로도 하는 듯..
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecognizer.startListening(intent);
                stext.setText(null);
            }
        });
        return view;


    }

    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float v) {
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int i) {
            stext.setText("바로 말해주세요!");
        }

        @Override
        public void onResults(Bundle bundle) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            stext.setText(rs[0]);
            if(!(vocabfragment.print.get(index).toString().equals(stext))){
                //발음을 5번 틀리면 WROMG 배열에 입력하기
            }
        }

        @Override
        public void onPartialResults(Bundle bundle) {
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    };
}
