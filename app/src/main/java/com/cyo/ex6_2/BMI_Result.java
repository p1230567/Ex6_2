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

    public BMI_Result() {
        super();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bmi_result, container, false);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        String result = getArguments().getString("result");
        tv_result.setText(result);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//           返回
            }
        });


        return view;
    }

}