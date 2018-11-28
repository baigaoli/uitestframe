package com.baigaoli.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by likaisong on 2018/11/27.
 */
public class TestNgRetry implements IRetryAnalyzer{
    private int retryCount = 0;
    private static int maxRetryCount = 2;

    public static void setTryCount(int count){
        maxRetryCount = count < 0 ? 0 : count;
    }
    @Override
    public boolean retry(ITestResult iTestResult) {
        boolean needRetry = this.retryCount < maxRetryCount;
        ++ this.retryCount;
        return needRetry;
    }
}
