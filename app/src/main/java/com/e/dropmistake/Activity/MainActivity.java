package com.e.dropmistake.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.dropmistake.Algorithm.Neo;
import com.e.dropmistake.Algorithm.Split;
import com.e.dropmistake.R;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecommendFragment.OnFragmentInteractionListener {
    //private EditText url1,url2,url3;
    private EditText inputMsg;
    // List<String> url=new ArrayList<String>(2);
    ArrayList<String>  stop_list = new ArrayList<>();
    String[] url = new String[6];
    String[] point = new String[3];
    Neo Server = new Neo("bolt://39.97.253.73:7687", "neo4j", "20200702");
    //老曹返回的答案String数组
    String[] returnURL = new String[6];
    private View.OnKeyListener onKey = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            // TODO Auto-generated method stub
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //这里写发送信息给jth的方法
                String sendText = inputMsg.getText().toString();
                //将输入框获得内容sendText发送给jth
                //returnURL=老曹.function();
                receiveString(view,returnURL);
                return true;
            } else return false;
        }

        ;
    };

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //搜索输入文本框
        inputMsg = findViewById(R.id.editText_view_input_search);
        //设置事件：按回车发送给jth
        inputMsg.setOnKeyListener(onKey);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //老曹开始
        InputStream addStream = getResources().openRawResource(R.raw.addwords);
        Split.readFromTxt(addStream);
        Toast.makeText(getApplicationContext(), "数学词汇添加完成", Toast.LENGTH_LONG).show();
        InputStream stopStream = getResources().openRawResource(R.raw.stop_words1);
        stop_list = Split.get_stop_list(stopStream);
        Toast.makeText(getApplicationContext(), "停用词表构建完成", Toast.LENGTH_LONG).show();
        //老曹结束
    }
        //接收到参数
        protected void receiveString(View view,String[] strings){
        //老曹的函数开始
            String[] resultString =Split.striped(inputMsg.getText().toString(),stop_list);
            String resultStr = String.join(" ",resultString);
            url = Server.key2klg(resultStr); //resultStr  words.getText().toString()
            point = Server.getKlgcs();
        //老曹的函数结束

        /*老曹调用它的类函数返回strings数组，或者ArrayList作为url[0]-url[5]的参数*/
         /*List<String> ex1=new ArrayList<String>(2);
                String[] ex1=new String[2];
                String[] ex2=new String[2];
                String[] ex3=new String[2];*/
//            url[0] = new String("http://image.fclassroom.com/1668/2018/1/272609/title_22_3298x2071_20180130133415967.png");
//            url[1] = new String("http://image.fclassroom.com/1668/2018/1/272887/title_8_3463x1431_20180130172721080.png");
//            url[2] = new String("http://image.fclassroom.com/1668/2018/3/291883/title_21_3298x2211_20180320103745741.png");
//            url[3] = new String("http://image.fclassroom.com/1668/2018/1/272609/answer_976173_22_1663x528_201801301043558915.png");
//            url[4] = new String("http://image.fclassroom.com/1668/2018/1/272887/answer_976176_8_707x423_201801301726434992.png");
//            url[5] = new String("http://image.fclassroom.com/1668/2018/3/291883/answer_976173_21_1121x513_201803201021040151.png");
//
//            point[0] = new String("知识点1");
//            point[1] = new String("知识点2");
//            point[2] = new String("知识点3");


            RecommendFragment recommendfragment = (RecommendFragment) RecommendFragment.newInstance(url, point);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_web, recommendfragment);
            fragmentTransaction.commit();
        }




        private TextView mTextMessage;

        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        return true;
                    case R.id.navigation_dashboard:
                        mTextMessage.setText(R.string.title_dashboard);
                        return true;
                    case R.id.navigation_notifications:
                        mTextMessage.setText(R.string.title_notifications);
                        return true;
                }
                return false;
            }
        };


        public void onClick(View view) {
            //相机点击拍照事件函数
        }

        @Override
        public void onFragmentInteraction(Uri uri) {

        }

        public void onKey(View view) {
        }
    }
