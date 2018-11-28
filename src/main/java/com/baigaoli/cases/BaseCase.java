package com.baigaoli.cases;

import com.baigaoli.util.Constants;
import com.baigaoli.util.DriverEngine;
import com.baigaoli.util.TestNgRetry;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Created by likaisong on 2018/11/6.
 */
public class BaseCase {

    @BeforeClass
    public void init(){
        TestNgRetry.setTryCount(Constants.TRY_COUNT);

    }

    @BeforeMethod
    public void initDriver(){
        DriverEngine.initDriver();
    }

    @AfterMethod
    public void closeDriver(){
        DriverEngine.closeDriver();
    }
}
