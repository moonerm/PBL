package com.test.pbl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private long pressedTime=0;
    private TextView mTextMessage;
    private DrawerLayout drawerLayout;

    public interface OnBackPressedListener{
        public void onBack();
    }

    private OnBackPressedListener mBackPressedListener;

    public  void setOnBackPressedListener(OnBackPressedListener listener){
        mBackPressedListener = listener;
    }

    @Override
    public void onBackPressed(){
        if(mBackPressedListener != null){
            mBackPressedListener.onBack();
            Log.e("!!!", "Listener is not null");
        } else {
            Log.e("!!!", "Listener is null");
            if (pressedTime == 0){
                Snackbar.make(findViewById(R.id.main_layout), "한 번 더 누르면 종료됩니다.", Snackbar.LENGTH_LONG).show();
                pressedTime = 1;
            } else {
                super.onBackPressed();
                Log.e("!!!", "onBackPressed : finish, killProcess");
                android.os.Process.killProcess(android.os.Process.myPid());
                finish();
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    mTextMessage.setText("홈입니당");
                    break;
                case R.id.bottom_decibel:
                    mTextMessage.setText("데시벨측정");
                    break;
                case R.id.bottom_clearly:
                    mTextMessage.setText("정확하게발음하시고요 !");
                    break;
                case R.id.bottom_mypage:
                    mTextMessage.setText("제 페이지예용");
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

//        LinearLayout main = (LinearLayout)findViewById(R.id.main);
//        LinearLayout decibel = (LinearLayout)findViewById(R.id.decibel);
//        LinearLayout clearly = (LinearLayout)findViewById(R.id.clearly);
//        LinearLayout mypage = (LinearLayout)findViewById(R.id.mypage);
//
//
//        main.setOnClickListener(new android.view.View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.main_fragment, new homefragment()).commit();
//            }
//        });
//
//        decibel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.main_fragment, new decibelfragment()).commit();
//            }
//        });
//
//        clearly.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.main_fragment, new clearlyfragment()).commit();
//            }
//        });
//
//        mypage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.main_fragment, new mypagefragment()).commit();
//            }
//        });


        mTextMessage = (TextView) findViewById(R.id.message);


        //BottomNavigationView
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        mTextMessage.setText("홈입니당");
                        break;
                    case R.id.bottom_decibel:
                        mTextMessage.setText("데시벨 측정해용");
                        break;
                    case R.id.bottom_clearly:
                        mTextMessage.setText("발음교정하시고요");
                        break;
                    case R.id.bottom_mypage:
                        mTextMessage.setText("내페이지입니다");
                        break;
                }
                return true;
            }
        });
//        bottombar navigation option
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);


        //Side bar NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.right_side);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_alram){
                    getFragmentManager().beginTransaction().replace(R.id.main_fragment,new alramfragment()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }


                if(item.getItemId() == R.id.menu_theme){
                    getFragmentManager().beginTransaction().replace(R.id.main_fragment, new themefragment()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });
    }
}
