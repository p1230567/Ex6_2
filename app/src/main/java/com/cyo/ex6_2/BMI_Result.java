package com.cyo.ex6_2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by USER on 2015/4/3.
 */
public class BMI_Result extends Fragment {
    private Button btn_back;
    private TextView tv_result;

  public BMI_Result(){
      super();
  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bmi_result, container, false);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              返回
                BMI_Result.this.finish();
            }
        });

        return view;
    }

    protected void findViews() {




    }

    private void showinfo() {
//      新建bundle取得intent內容
        Bundle bundle = this.getIntent().getExtras();
//        取得傳過來的Double物件，key為"hight"、weight
        Double hight = bundle.getDouble("hight");
        Double weight = bundle.getDouble("weight");
//        將Double物件轉成##.##的String物件
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
//        計算BMI
        Double BMI = weight / (hight * hight);

        if (BMI < 18.5) {
            tv_result.setText("BMI值為:" + decimalFormat.format(BMI) + "\n" + "過瘦");
        } else if (BMI >= 24) {
            tv_result.setText("BMI值為:" + decimalFormat.format(BMI) + "\n" + "過重");
        } else {
            tv_result.setText("BMI值為:" + decimalFormat.format(BMI) + "\n" + "正常");
        }


    }

}