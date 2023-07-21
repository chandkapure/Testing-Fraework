package com.paywallet.Utilities;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RunFailedTestcases implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstroctor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}






