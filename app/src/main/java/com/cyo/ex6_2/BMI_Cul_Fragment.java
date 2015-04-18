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
    private final static String TAG = "fragment";
    private EditText et_hight, et_weight;
    private Button btn_sumbit, btn_clear;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bmi_cul, container, false);
        findVIews();


        return view;
    }

    protected void findVIews() {
        btn_sumbit = (Button) view.findViewById(R.id.btn_sumbit);
        et_hight = (EditText) view.findViewById(R.id.et_hight);
        btn_clear = (Button) view.findViewById(R.id.btn_clear);
        et_weight = (EditText) view.findViewById(R.id.et_weight);

        btn_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        try {
//                    取得文字字串、Double的內容
            String hight = et_hight.getText().toString();
            String weight = et_weight.getText().toString();
            Double double_Hight = Double.parseDouble(hight);
            Double double_weight = Double.parseDouble(weight);
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
//        計算BMI
            Double BMI = double_weight / (double_Hight * double_Hight);
            String result = "";
            if (BMI < 18.5) {
                result = "BMI值為:" + decimalFormat.format(BMI) + "\n" + "過瘦";
            } else if (BMI >= 24) {
                result = "BMI值為:" + decimalFormat.format(BMI) + "\n" + "過重";
            } else {
                result = "BMI值為:" + decimalFormat.format(BMI) + "\n" + "正常";
            }
            Bundle bundle = new Bundle();
            bundle.putString("result", result);
            BMI_Result bmi_result = new BMI_Result();
            bmi_result.setArguments(bundle);
            transaction.add(R.id.MainActivity, bmi_result, TAG);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();


        } catch (Exception e) {
//                    例外處理，提醒文字及清空EditView
//            Toast.makeText(MainActivity, "資料有誤", Toast.LENGTH_SHORT).show();
            et_weight.setText("");
            et_hight.setText("");
            return;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
