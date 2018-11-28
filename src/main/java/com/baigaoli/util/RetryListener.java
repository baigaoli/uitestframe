package com.baigaoli.util;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by likaisong on 2018/11/27.
 */
public class RetryListener extends TestListenerAdapter implements IAnnotationTransformer{
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        IRetryAnalyzer retryAnalyzer = iTestAnnotation.getRetryAnalyzer();
        if (retryAnalyzer == null){
            iTestAnnotation.setRetryAnalyzer(TestNgRetry.class);
        }
    }
}
