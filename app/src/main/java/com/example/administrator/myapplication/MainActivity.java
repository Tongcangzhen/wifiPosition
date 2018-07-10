package com.example.administrator.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.ScanResult;

import java.util.Date;
import java.util.List;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.enerty.WifiPos;

import org.litepal.tablemanager.Connector;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 0;
    private WifiManager wm;
    EditText editText1 ;
    EditText editText2;
    EditText editText3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector.getDatabase();
        requestLocationPermission();
        editText3 = (EditText) findViewById(R.id.et3);
        button = (Button) findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savelist();
            }
        });
        new tvThread().start();
    }

    public void requestLocationPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Toast.makeText(MainActivity.this, "自Android 6.0开始需要打开位置权限才可以搜索到WIFI设备", Toast.LENGTH_SHORT);

                }
                //请求权限
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_CODE_ACCESS_COARSE_LOCATION);
            }
        }
    }


    private class tvThread extends Thread {
        @Override
        public void run() {
            while (true) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        obtainListInfo();
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void obtainListInfo(){
        //---------------------------------------------->

        editText1 = (EditText) findViewById(R.id.et1);
        editText2 = (EditText) findViewById(R.id.et2);
        //显示扫描到的所有wifi信息
        wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();

        int strength = wi.getRssi();
        int speed = wi.getLinkSpeed();
        String designation = wi.getSSID();

        String addr = wi.getBSSID();
        String unit = WifiInfo.LINK_SPEED_UNITS;

        if (wm.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            StringBuilder listinfo = new StringBuilder();
            //搜索到的wifi列表信息
            List<ScanResult> scanResults = wm.getScanResults();

            for (ScanResult sr : scanResults) {
                listinfo.append("wifi网络ID：");
                listinfo.append(sr.SSID);
                listinfo.append("\nwifi MAC地址：");
                listinfo.append(sr.BSSID);
                listinfo.append("\nwifi信号强度：");
                listinfo.append(sr.level + "\n\n");
            }
            editText2.setText(listinfo.toString());
            String curr_connected_wifi = null;
            curr_connected_wifi = "Currently connecting WiFi \'" + designation + "\' \nRssi: " + strength +
                    "\nMac addr: " + addr + "\nspeed: " + speed + " " + unit;
            editText1.setText(curr_connected_wifi.toString());
        } else {
            editText2.setText("找不到wifi");
            editText1.setText("找不到wifi");

        }
        //------------------------------------------------------------------->
    }

    private void savelist() {
        WifiPos wifiPos = new WifiPos();
        wifiPos.setClassId(editText3.getText().toString());
        wifiPos.setWifiResult(editText2.getText().toString());
        wifiPos.setTime(new Date());
        wifiPos.save();
    }

}
