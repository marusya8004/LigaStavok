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
        System.out.println("Now starting test " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Successfully completed test: " + result.getName());
        MultiToneChrome.getInstance().destroy();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.always();
        System.out.println("*** execution of: " + result.getMethod().getMethodName() + " failed!");
        MultiToneChrome.getInstance().destroy();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.always();
        System.out.println("Test Name: " + result.getName() + "skipped!");
        MultiToneChrome.getInstance().destroy();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Name: " + result.getName() + " failed but within success percentage");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Name: " + context.getName() + " are starting.");
        ITestNGMethod[] methods = context.getAllTestMethods();
        System.out.println("This methods will be executed in this test tag:");
        for (ITestNGMethod method : methods)
            System.out.println(method.getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test  " + context.getName() + " on finish");
        MultiToneChrome.getInstance().destroy();
    }
}