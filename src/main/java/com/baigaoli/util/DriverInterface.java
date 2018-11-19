package com.baigaoli.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by likaisong on 2018/11/19.
 */
public interface DriverInterface {
    /**
     * 初始化Driver
     * @param url
     * @param desiredCapabilities
     * @return
     */
    AppiumDriver initDriver(URL url, DesiredCapabilities desiredCapabilities);
    /**
     * 上滑一屏
     * @param driver
     * @param origin
     * @param distance
     */
    void swipeUpFromPercent(AppiumDriver driver, int origin, int distance);

    /**
     * 下滑一屏
     * @param driver
     * @param origin
     * @param distance
     */
    void swipeDownFromPercent(AppiumDriver driver, int origin, int distance);

    /**
     * 输入字符串
     * @param mobileElement
     * @param value
     */
    void setValue(MobileElement mobileElement, String value);

    /**
     * 屏幕录制开始
     */
    void startRecordingScreen();

    /**
     * 屏幕录制结束
     */
    void stopRecordingScreen();

    /**
     * 录制文件存储
     * @param path
     * @param recordingName
     */
    void pullRecordingScreen(String path, String recordingName);
}
