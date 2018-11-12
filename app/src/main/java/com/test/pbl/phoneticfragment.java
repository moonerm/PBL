package com.test.pbl;

import android.Manifest;
import android.app.Fragment;
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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class phoneticfragment extends Fragment {
    Intent intent;
    SpeechRecognizer mRecognizer;
    TextView textView;
    ImageButton button;
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


        textView = (TextView)view.findViewById(R.id.textView);//fVBI를 fragment로 바꿀때: getView()를 앞에 붙인다

        button = (ImageButton)view.findViewById(R.id.button1);//view.으로도 하는 듯..
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecognizer.startListening(intent);
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
            textView.setText("너무 늦게 말하면 오류뜹니다");

        }

        @Override
        public void onResults(Bundle bundle) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            textView.setText(rs[0]);
        }

        @Override
        public void onPartialResults(Bundle bundle) {
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    };
}
