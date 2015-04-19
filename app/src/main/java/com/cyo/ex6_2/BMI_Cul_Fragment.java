package com.cyo.ex6_2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by USER on 2015/4/18.
 */
public class BMI_Cul_Fragment extends Fragment {
    private View view;
    private EditText et_hight, et_weight;
    private Button btn_sumbit, btn_clear;


    //  當這個Fragment VIEW呈現時要執行的layout 以及各UI要執行的動作在此設定
//    系统在Fragment第一次繪製其介面时使用用這個方法。要繪製的UI就必須從這個方法返回一個佈局的View，
//      如果Fragment不提供UI，可以只返回一個null。
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//      將這個做好的view回傳inflater到container(Mainactivity)上，並且使用false表示不貼在container上回傳
        view = inflater.inflate(R.layout.bmi_cul, container, false);
        findVIews();
        return view;
    }

    protected void findVIews() {
        btn_sumbit = (Button) view.findViewById(R.id.btn_sumbit);
        et_hight = (EditText) view.findViewById(R.id.et_hight);
        btn_clear = (Button) view.findViewById(R.id.btn_clear);
        et_weight = (EditText) view.findViewById(R.id.et_weight);
//      按下送出的按鈕之後的事件
        btn_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//             按下按鍵之後新增一個fragment
                addFragment();

            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                清除按鍵
                et_weight.setText("");
                et_hight.setText("");
            }
        });

    }

    private void addFragment() {

//      try-catch內計算BMI
//        result存放計算結果的字串
        String result;
        try {
//                    取得文字字串、Double的內容
            String hight = et_hight.getText().toString();
            String weight = et_weight.getText().toString();
            Double double_Hight = Double.parseDouble(hight);
            Double double_weight = Double.parseDouble(weight);
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
//        計算BMI
            Double BMI = double_weight / (double_Hight * double_Hight);

            if (BMI < 18.5) {
                result = "BMI值為:" + decimalFormat.format(BMI) + "\n" + "過瘦";
            } else if (BMI >= 24) {
                result = "BMI值為:" + decimalFormat.format(BMI) + "\n" + "過重";
            } else {
                result = "BMI值為:" + decimalFormat.format(BMI) + "\n" + "正常";
            }


        } catch (Exception e) {
//                    例外處理，提醒文字及清空EditView
//            利用getActivity取得content
            Toast.makeText(getActivity(), "資料有誤", Toast.LENGTH_SHORT).show();
            et_weight.setText("");
            et_hight.setText("");
            return;
        }
//       建立bundle傳送資料
        Bundle bundle = new Bundle();
//        放入到(K,V)
        bundle.putString("result", result);
//        上面處理完之後，準備要傳送到result，建立該fragment
        BMI_Result bmi_result = new BMI_Result();
//          利用setArguments傳遞參數資料
        bmi_result.setArguments(bundle);
//        直接取得FragmentManager，及beginTransaction去replace掉bmi_result，最後提交
        getFragmentManager().beginTransaction().replace(R.id.MainActivity, bmi_result).commit();


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
