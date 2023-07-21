package com.paywallet.Utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;

    @Override public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            int maxTry = 2;
            if (count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);

                iTestResult.getTestContext().getFailedTests().removeResult(iTestResult);

                return true;
            } else {
                iTestResult
                        .setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult
                    .setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
