package com.baigaoli.cases;

import com.baigaoli.util.DriverEngine;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by likaisong on 2018/11/6.
 */
public class BaseCase {

    @BeforeMethod
    public void initDriver(){
        DriverEngine.initDriver();
    }

    @AfterMethod
    public void closeDriver(){
        DriverEngine.closeDriver();
    }
}
