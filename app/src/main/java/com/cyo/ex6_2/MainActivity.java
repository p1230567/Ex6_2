package com.cyo.ex6_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText et_hight, et_weight;
    private Button btn_sumbit, btn_clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findVIews();
    }

    protected void findVIews() {
        btn_sumbit = (Button) findViewById(R.id.btn_sumbit);
        et_hight = (EditText) findViewById(R.id.et_hight);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        et_weight = (EditText) findViewById(R.id.et_weight);

        btn_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                設定該按鍵點擊時intent到BMI_Result
                intent.setClass(MainActivity.this, BMI_Result.class);
//                new夾帶資料包裹
                Bundle bundle = new Bundle();
                try {
//                    取得文字字串、Double的內容
                    String hight = et_hight.getText().toString();
                    String weight = et_weight.getText().toString();
                    Double double_Hight = Double.parseDouble(hight);
                    Double double_weight = Double.parseDouble(weight);
//                    未輸入的例外處理
                    if (hight.isEmpty() || weight.isEmpty()) {
                        throw new Exception();
//                        輸入範圍例外處理
                    } else if (double_Hight <= 0 | double_Hight >= 3 || double_weight <= 0 | double_weight >= 500) {
                        throw new Exception();
                    }
//將資料放入bundle(K,V)
                    bundle.putDouble("hight", double_Hight);
                    bundle.putDouble("weight", double_weight);

                } catch (Exception e) {
//                    例外處理，提醒文字及清空EditView
                    Toast.makeText(MainActivity.this, "資料有誤", Toast.LENGTH_SHORT).show();
                    et_weight.setText("");
                    et_hight.setText("");
                    return;
                }

                intent.putExtras(bundle);
                startActivity(intent);


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
