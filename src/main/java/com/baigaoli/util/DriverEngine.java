package com.baigaoli.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by likaisong on 2018/11/6.
 */
public class DriverEngine {
    private static Logger logger = Logger.getLogger("DriverEngine");
    public static AppiumDriver<WebElement> driver;

    public static void initDriverWithCapabilities(DesiredCapabilities capabilities){
        int tryCnt = 3;
        logger.info("start to init driver. tryCnt = " + tryCnt);
        while(tryCnt-- > 0) {
            logger.info("tryCnt = " + tryCnt);
            closeDriver();
            try {
                if ("iOS".equals(capabilities.getCapability(Constants.PLATFORMNAME))) {
                    logger.info("creating iOS driver");
                    driver = new IOSDriver(new URL(Capabilities.getUrl()), capabilities);
                } else {
                    logger.info("creating Android driver");
                    driver = new AndroidDriver(new URL(Capabilities.getUrl()), capabilities);
                }
                logger.info("session id :" + driver.getSessionId());
                if (driver.getSessionId() != null) {
                    TimeUnit.SECONDS.sleep(5L);
                    break;
                }
            } catch (Exception var1) {
                logger.severe(var1.getMessage());
            }

            try {
                TimeUnit.SECONDS.sleep(70L);
            } catch (InterruptedException var2) {
                logger.severe(var2.getMessage());
            }
        }
        if (driver != null && driver.getSessionId() != null) {
            logger.info("建立会话连接成功");
        } else {
            logger.info("建立会话连接失败");
        }
    }

    public static void initDriver(){
        DesiredCapabilities capabilities = Capabilities.getCapabilities();
        initDriverWithCapabilities(capabilities);
        logger.info("app启动参数：" + driver.getCapabilities());
    }

    public static AppiumDriver<WebElement> getDriver(){
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            logger.info("try to close driver");
            driver.quit();
            StringBuilder builder = (new StringBuilder()).append("after close driver, sessionId = ");
            logger.info(builder.append(driver.getSessionId()).toString());
        }
    }
}
