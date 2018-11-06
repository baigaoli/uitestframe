package com.baigaoli.util;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by likaisong on 2018/11/6.
 */
public class Capabilities {
    public static final String PROTERTIES_FILE_NAME = "app.properties";
    public static final String PLATFORMNAME = "platformName";
    public static final String[]  capabilitiesKeys = new String[]{"automationName", "platformName", "platformVersion", "deviceName", "app",
            "browserName", "newCommandTimeout", "language", "locale", "udid", "orientation", "autoWebview", "noReset", "fullReset",
            "appActivity", "appPackage", "appWaitActivity", "appWaitPackage", "deviceReadyTimeout", "androidCoverage",
            "enablePerformanceLogging", "androidDeviceReadyTimeout", "androidInstallTimeout", "adbPort", "androidDeviceSocket",
            "avd", "avdLaunchTimeout", "avdReadyTimeout", "avdArgs", "useKeystore", "keystorePath", "keystorePassword", "keyAlias",
            "keyPassword", "chromedriverExecutable", "autoWebviewTimeout", "intentAction", "intentCategory", "intentFlags",
            "optionalIntentArguments", "dontStopAppOnReset", "unicodeKeyboard", "resetKeyboard", "noSign", "ignoreUnimportantViews",
            "disableAndroidWatchers", "chromeOptions", "recreateChromeDriverSessions", "nativeWebScreenshot", "androidScreenshotPath",
            "calendarFormat", "bundleId", "udid", "launchTimeout", "locationServicesEnabled", "locationServicesAuthorized", "autoAcceptAlerts",
            "autoDismissAlerts", "nativeInstrumentsLib", "nativeWebTap", "safariInitialUrl", "safariAllowPopups", "safariIgnoreFraudWarning",
            "safariOpenLinksInBackground", "keepKeyChains", "localizableStringsDir", "processArguments", "interKeyDelay", "showIOSLog",
            "sendKeyStrategy", "screenshotWaitTimeout", "waitForAppScript", "webviewConnectRetries", "appName", "xcodeConfigFile",
            "keychainPath", "keychainPassword", "scaleFactor", "usePrebuiltWDA", "wdaConnectionTimeout", "wdaLaunchTimeout", "useNewWDA",
            "preventWDAAttachments", "wdaLocalPort", "xcodeOrgId", "xcodeSigningId", "commandTimeouts", "wdaStartupRetries",
            "wdaStartupRetryInterval", "shouldUseSingletonTestManager", "useXctestrunFile"};
    public static Set<String> capaKeys = new HashSet<String>(Arrays.asList(capabilitiesKeys));
    public static Properties properties = getProperties(PROTERTIES_FILE_NAME);

    public static String getUrl(){
        return String.format("http://127.0.0.1:%s/wd/hub", properties.getProperty("port"));
    }

    public static Properties getProperties(String capaName){
        if (capaName == null || "".equals(capaName)) {
            capaName = "app.properties";
        }
        if(properties == null){
            properties = new Properties();
        }
        try {
            properties.load(new FileInputStream(capaName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static DesiredCapabilities getCapabilities(){
        return getCapabilitiesByProperties(properties);
    }

    public static DesiredCapabilities getCapabilitiesByProperties(Properties properties){
        Map<String, String> map = new HashMap(properties);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        map.entrySet().stream().filter((entry) -> {
            return capaKeys.contains(entry.getKey());
        }).forEach((entry) -> {
            capabilities.setCapability((String)entry.getKey(), (String)entry.getValue());
        });
        return capabilities;
    }



}
