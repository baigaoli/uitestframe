package com.baigaoli.pages;

import com.baigaoli.util.Constants;
import com.baigaoli.util.DriverEngine;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

/**
 * Created by likaisong on 2018/11/7.
 */
public class BasePage {

    /**
     * 初始化页面元素
     */
    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverEngine.getDriver(), Duration.ofSeconds(Constants.TIME_OUT)), this);
    }
}