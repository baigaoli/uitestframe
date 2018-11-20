package com.baigaoli.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * Created by likaisong on 2018/11/19.
 */
public abstract class DriverUtil {
    private static Logger logger = Logger.getLogger("DriverUtil");
    /**
     * 初始化Driver
     *
     * @param url
     * @param desiredCapabilities
     * @return
     */
    protected  abstract AppiumDriver initDriver(URL url, DesiredCapabilities desiredCapabilities);

    /**
     * 上滑一屏
     * @param driver
     * @param origin
     * @param distance
     */
    public void swipeUpFromPercent(AppiumDriver driver, int origin, int distance){
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

    /**
     * 下滑一屏
     * @param driver
     * @param origin
     * @param distance
     */
    public void swipeDownFromPercent(AppiumDriver driver, int origin, int distance){
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

    /**
     * 输入字符串
     * @param mobileElement
     * @param value
     */
    public void setValue(MobileElement mobileElement, String value){
        mobileElement.clear();
        mobileElement.sendKeys(new CharSequence[]{value});
    }
}
