package initialDriver;

import io.qameta.allure.listener.LifecycleListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class ListenerITest implements ITestListener, LifecycleListener {
    private static final Logger log = LogManager.getLogger(ListenerITest.class.getName());

    @Override
    public void onTestStart(ITestResult result) {
        log.always();
        log.info("Now starting test " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Successfully completed test: " + result.getName());
        MultiToneChrome.getInstance().destroy();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.always();
        log.info("*** execution of: " + result.getMethod().getMethodName() + " failed!");
        MultiToneChrome.getInstance().destroy();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.always();
        log.info("Test Name: " + result.getName() + "skipped!");
        MultiToneChrome.getInstance().destroy();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info("Test Name: " + result.getName() + " failed but within success percentage");
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test Name: " + context.getName() + " are starting.");
        ITestNGMethod[] methods = context.getAllTestMethods();
        log.info("This methods will be executed in this test tag:");
        for (ITestNGMethod method : methods)
            log.info(method.getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test  " + context.getName() + " on finish");
        MultiToneChrome.getInstance().destroy();
    }
}