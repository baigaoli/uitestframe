package com.baigaoli.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by likaisong on 2018/11/19.
 */
public class AndroidDriverUtil extends DriverUtil{
    private static Logger logger = Logger.getLogger("AndroidDriverUtil");
    private AndroidScreenRecord androidScreenRecord;

    @Override
    public AppiumDriver initDriver(URL url, DesiredCapabilities desiredCapabilities) {
        logger.info("create Android driver");
        AppiumDriver driver = null;
        try {
            driver = new AndroidDriver(url, desiredCapabilities);
        }catch (Exception e){
            logger.severe("建立连接失败：" + e.getMessage());
        }
        if (driver.getSessionId() != null){
            logger.info("建立会话成功");
        }
        return driver;
    }

    public void startRecordingScreen() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    AndroidDriverUtil.this.androidScreenRecord = new AndroidScreenRecord();
                    AndroidDriverUtil.this.androidScreenRecord.startRecordingScreenLongTime();
                }catch (Exception e){
                    logger.severe("录制视频异常: "+ e.getMessage());
                }
                super.run();
            }
        };
        thread.start();
    }

    public void stopRecordingScreen() throws IOException {
        androidScreenRecord.stopRecordingScreen();
    }

    public void pullRecordingScreen(String path, String recordingName) {
        androidScreenRecord.pullRecordingScreen(path, recordingName);
    }
}

