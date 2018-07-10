package com.example.administrator.myapplication;

import com.example.administrator.myapplication.enerty.WifiPos;

import org.junit.Test;
import org.litepal.tablemanager.Connector;

import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void additsCorrect() throws Exception {
        for(int i=0;i<10;i++) {
            WifiPos wifiPos = new WifiPos();
            wifiPos.setClassId(1);
            wifiPos.setTime(new Date());
            wifiPos.setWifiResult("aaaa" + i);
            wifiPos.save();
        }
    }
}