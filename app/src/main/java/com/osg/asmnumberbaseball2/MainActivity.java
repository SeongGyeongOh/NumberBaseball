package com.osg.asmnumberbaseball2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //xml 문서 안의 객체들을 참조하는 참조변수
    TextView tv;
    EditText et1, et2, et3;
    Button btn, btn2;
    int com1, com2, com3;
    int strike;
    int ball;
    String result = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
//        av=findViewById(R.id.adv);


        //xml에 만든 뷰들을 참조변수에 대입
        tv = findViewById(R.id.result);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);

        //컴퓨터 랜덤 값 설정
        Random rnd = new Random();
        do{
            com1 = rnd.nextInt(9)+1;
            com2  = rnd.nextInt(9)+1;
            com3  = rnd.nextInt(9)+1;
        }while (com1==com2||com1==com3||com2==com3);
    }

    public void clickRestart(View view) {
        btn2.setVisibility(View.GONE);
        btn.setVisibility(View.VISIBLE);

        result="";
        tv.setText(result);

        //컴퓨터 랜덤 값 설정
        Random rnd = new Random();
        do{
            com1 = rnd.nextInt(9)+1;
            com2  = rnd.nextInt(9)+1;
            com3  = rnd.nextInt(9)+1;
        }while (com1==com2||com1==com3||com2==com3);
    }

    public void clickBtn(View view) {
        strike =0;
        ball = 0;

        //먼저 입력값을 얻어와 int형으로 바꾸자
        String s1 =et1.getText().toString();
        String s2 = et2.getText().toString();
        String s3 = et3.getText().toString();

        if(s1.isEmpty()||s2.isEmpty()||s3.isEmpty()){
            Log.i("왜안돼", "안됨");
            Toast.makeText(this, "세 개의 값 모두 입력하세요", Toast.LENGTH_SHORT).show();
        }else{
            Log.i("왜안돼", "왜 여기가 됨");
            int input1 = Integer.parseInt(s1);
            int input2 = Integer.parseInt(s2);
            int input3 = Integer.parseInt(s3);
            //입력 값과 com 값을 비교하자
            if (com1==input1) strike++;
            if (com2==input2) strike++;
            if (com3==input3) strike++;

            if (com1==input2 || com1 ==input3) ball++;
            if (com2==input1 || com2 ==input3) ball++;
            if (com3==input1 || com3 ==input2) ball++;


            result+=strike+"Strike  "+ ball+"Ball\n";
            tv.setText(result);

            if(com1==input1 && com2==input2 && com3==input3) {
                tv.setText(result+"축하합니다 \n정답입니다!");
                btn2.setVisibility(View.VISIBLE);
                btn.setVisibility(View.GONE);



            }
        }
        et1.setText("");
        et2.setText("");
        et3.setText("");
    }


}
