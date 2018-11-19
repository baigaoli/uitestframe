package com.baigaoli.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * Created by likaisong on 2018/11/19.
 */
public class AndroidDriverUtil implements DriverInterface{
    private static Logger logger = Logger.getLogger("AndroidDriverUtil");
    private AndroidScreenRecord androidScreenRecord;

    @Override
    public AppiumDriver initDriver(URL url, DesiredCapabilities desiredCapabilities) {
        logger.info("create Android driver");
        AndroidDriver driver = null;
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

    @Override
    public void swipeUpFromPercent(AppiumDriver driver, int origin, int distance) {
        TouchAction action = new TouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        PointOption pressPoint = PointOption.point(width / 2, height / 100 * origin);
        PointOption movePoint = PointOption.point(width / 2, height / 100 * (origin - distance));
        WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(500L));
        action.press(pressPoint).waitAction(waitOptions).moveTo(movePoint).release();
        action.perform();
        logger.info("上滑一屏");
    }

    @Override
    public void swipeDownFromPercent(AppiumDriver driver, int origin, int distance) {
        TouchAction action = new TouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        PointOption pressPoint = PointOption.point(width / 2, height / 100 * origin);
        PointOption movePoint = PointOption.point(width / 2, height / 100 * (distance + origin));
        WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(500L));
        action.press(pressPoint).waitAction(waitOptions).moveTo(movePoint).release();
        action.perform();
        logger.info("下滑一屏");
    }

    @Override
    public void setValue(MobileElement mobileElement, String value) {
        mobileElement.clear();
        mobileElement.sendKeys(new CharSequence[]{value});
    }

    @Override
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

    @Override
    public void stopRecordingScreen() {
        androidScreenRecord.stopRecordingScreen();
    }

    @Override
    public void pullRecordingScreen(String path, String recordingName) {
        androidScreenRecord.pullRecordingScreen(path, recordingName);
    }
}

