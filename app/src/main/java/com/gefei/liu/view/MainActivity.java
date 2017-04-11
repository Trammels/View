package com.gefei.liu.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/****
 * 圆形图案
 * 日历界面
 *自定义Drawable
 * http://blog.csdn.net/pjbwan/article/details/45130307
 * Logger插件的用法
 * 快速使用toast插件，eg：imgView.toast
 *
 * */

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private TextView tv;
    private android.widget.LinearLayout activitymain;
    private android.widget.ImageView imgView;
    private android.widget.Button btnrandom;
    private List<String> list = new ArrayList<>();

    private Button btn_slidingmenu;
    private DrawerLayout drawerLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnrandom = (Button) findViewById(R.id.btn_random);
        this.imgView = (ImageView) findViewById(R.id.imgView);
        this.activitymain = (LinearLayout) findViewById(R.id.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        btn_slidingmenu = (Button) findViewById(R.id.btn_slidingmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        // 设置侧滑阴影透明度
        drawerLayout.setScrimColor(Color.argb(150, 192, 192, 192));
        initView();
        String json2 = "{\"2\":\"sfsdfsdfsfds\",\"1\":\"dsfdsfsd\"}";
        String json3 = "{\"22\":\"2\",\"1\":\"2\"}";
        Logger.json(json2);
        Logger.json(json3);

        Log.e(TAG, "onCreate: " + json2 + json3);
        /**
         * 生成随机的4位数
         * 两种写法都可以，ran<1000这个条件是为了随机数出现的值必须在大于1000的时候，才能输出
         * 否则就在生成一个随机的四位数
         * 这个会生成1000-9999之间的随机数，改动1000或者9999的值，可以影响这个随机数的范围
         * */
        btnrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
//                int ran = 0;
//                while (ran < 1000) {
//                    ran = random.nextInt(9999);
//                }
                for (int ran = 0; ran < 1000; ran++) {
                    ran = random.nextInt(9999);
                    btnrandom.setText(ran + "");
                }


                String sum[] = {"1", "2"};
//                for( String a:sum){
//                    if(a.equals("1")){
//                        tv.setText(a);
//                    }
//                }
                list.add("10");
                list.add("20");
                list.add("30");
//                for(String str :list){
//                    if(list.contains("20")){
//                        list.remove(str);
//                    }
//                }
//                tv.setText(list.toString());
//                for (int i = 0, size = list.size();i<list.size();i++){
//                    if(list.get(i).toString().contains("20")){
//                        list.remove(i);
//                        size--;
//                        i--;
//                    }
//                }
//                tv.setText(list.size()+"");
                /**
                 * 移除list中的一项
                 * **/
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (list.get(i).toString().contains("20")) {
                        list.remove(i);
                    }
                }
                tv.setText(list.size() + "" + list.toString());
                initGPS();
            }
        });


    }

    /***
     * 判断是否开启GPS
     */
    private void initGPS() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // 判断GPS模块是否开启，如果没有则开启
        if (!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(MainActivity.this, "请打开GPS", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("           您的定位功能没有开启");
            dialog.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // 转到手机设置界面，用户设置GPS
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(intent, 0);
                    arg0.dismiss();
                }
            });
            dialog.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });
            dialog.show();
        } else {
        }
    }


    public void initView() {

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);

            }
        });
        Toast.makeText(this, "tv:" + tv, Toast.LENGTH_SHORT).show();
        btn_slidingmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
//            drawerLayout.closeDrawer(Gravity.LEFT);
//        }

    }

    public void onclick(View v) {
        Intent intent = new Intent();
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
        switch (v.getId()) {
            case R.id.drawerLayout:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.myexit:
//                finish();
                break;
            case R.id.myusericon:
                Intent tousericon = new Intent();
                tousericon.setClass(MainActivity.this, MainActivity2.class);
                startActivity(tousericon);
                break;
            case R.id.home_record:
                intent.setClass(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.home_wallet:
                intent.setClass(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.home_message:
                intent.setClass(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.home_notice:
                intent.setClass(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 1);

                break;
            case R.id.home_mine:
                intent.setClass(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}
