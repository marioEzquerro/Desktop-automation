package com.project.www.support;

import com.project.www.pages.PagesFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;


@Slf4j
public class Hooks {
    private static WebDriver driver;

    @Before
    public static void before(Scenario scenario) {
        log.info("Starting scenario: " + scenario.getName());
        driver = initDriver();
        PagesFactory.start(driver);
    }

    @After
    public static void after(Scenario scenario) {
        log.info("Ending scenario: " + scenario.getName());

        if (scenario.isFailed())
            scenario.attach(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES),
                    "image/png",
                    "IMAGEN TEST FALLIDO - " + scenario.getName()
            );

        driver.quit();
    }

    // --------- DRIVER CONFIGURATION --------- \\
    private static WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        // These capabilities allow access to insecure cerfificates in the web
        ChromeOptions capability = new ChromeOptions();
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        WebDriver driver = new ChromeDriver(capability);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
}
