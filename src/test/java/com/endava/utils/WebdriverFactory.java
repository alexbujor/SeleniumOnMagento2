package com.endava.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebdriverFactory {

    private static Logger log = LoggerFactory.getLogger(WebdriverFactory.class);
    private static TestConfig testConfig;

    public static WebDriver initDriver(TestConfig config) {
        testConfig = config;
        log.info(config.toString());

        switch (config.getSeleniumServerType()) {
            case LOCAL:
                switch (config.getBrowserType()) {
                    case FIREFOX:
                        return new FirefoxDriver();
                    case CHROME:
                        System.setProperty("webdriver.chrome.driver", testConfig.getChromedriverPath());
                        return new ChromeDriver();
                    case IE:
                        return new InternetExplorerDriver();
                    default:
                        return new FirefoxDriver();

                }

            case REMOTE:
                switch (config.getBrowserType()) {

                    case FIREFOX:
                        return new ScreenShotRemoteWebDriver(config.getSeleniumServerURL(), DesiredCapabilities.firefox());
                    case CHROME:
                        return new ScreenShotRemoteWebDriver(config.getSeleniumServerURL(), DesiredCapabilities.chrome());
                    case IE:
                        return new ScreenShotRemoteWebDriver(config.getSeleniumServerURL(), DesiredCapabilities.internetExplorer());
                    default:
                        return new ScreenShotRemoteWebDriver(config.getSeleniumServerURL(), DesiredCapabilities.firefox());

                }

            default:
                return new FirefoxDriver();
        }
    }
}
