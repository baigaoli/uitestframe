package com.baigaoli.util;

/**
 * Created by likaisong on 2018/11/7.
 */
public class Constants {
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
    public static final String IP = ConfigHelper.getgetPropertyValue(PROTERTIES_FILE_NAME, "ip") == null ? "http://127.0.0.1" : ConfigHelper.getgetPropertyValue(PROTERTIES_FILE_NAME, "ip").toString();
    public static final String PORT = ConfigHelper.getgetPropertyValue(PROTERTIES_FILE_NAME, "port") == null ? "4723" : ConfigHelper.getgetPropertyValue(PROTERTIES_FILE_NAME, "port").toString();
    public static final long TIME_OUT = 3;
}
