package com.cyo.ex6_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
//利用開啟APP後就create第一個fragment，之後則利用replace進行兩個fragment之間的轉換

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        onCreate MainActivity之後直接add Fragment
//        new 計算頁的BMI_Cul_Fragment 的Fragment
//        先做好各Fragment的佈局XML

        BMI_Cul_Fragment BMI_Cul_Fragment = new BMI_Cul_Fragment();
//        取得Fragment管理，開始傳輸事項，在欲傳輸的功能添加add增加BMI_Cul_Fragment()這個Fragment
//        在R.id.MainActivity上，最後commit提交，如此一來一進入APP則立即呈現
//        BMI_Cul_Fragment這個Fragment

        getFragmentManager().beginTransaction().add(R.id.MainActivity, BMI_Cul_Fragment).commit();

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
