package initialDriver;

import org.openqa.selenium.chrome.ChromeOptions;

abstract class Options {

    ChromeOptions chromeOptions(boolean headlessMod) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        if (headlessMod) {
            options.addArguments("--headless");
        }
        return options;
    }
}
