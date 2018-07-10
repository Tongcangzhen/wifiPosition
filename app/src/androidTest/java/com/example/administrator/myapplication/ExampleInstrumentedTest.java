package com.example.administrator.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.administrator.myapplication.enerty.WifiPos;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.administrator.myapplication", appContext.getPackageName());
//        for(int i=0;i<10;i++) {
//            WifiPos wifiPos = new WifiPos();
//            wifiPos.setClassId(1);
//            wifiPos.setTime(new Date());
//            wifiPos.setWifiResult("aaaa" + i);
//            wifiPos.save();
//        }
    }
}
