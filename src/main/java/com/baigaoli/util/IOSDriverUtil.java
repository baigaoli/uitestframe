package com.baigaoli.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by likaisong on 2018/11/20.
 */
public class IOSDriverUtil extends DriverUtil{
    private static Logger logger = Logger.getLogger("IOSDriverUtil");
    @Override
    public  AppiumDriver initDriver(URL url, DesiredCapabilities desiredCapabilities) {
        logger.info("create ios driver");
        AppiumDriver driver = null;
        try { driver = new IOSDriver(url, desiredCapabilities);
        }catch (Exception e){
            logger.severe("建立连接失败：" + e.getMessage());

        }
        if (driver.getSessionId() != null){
            logger.info("建立会话成功");
        }
        return driver;
    }
}
