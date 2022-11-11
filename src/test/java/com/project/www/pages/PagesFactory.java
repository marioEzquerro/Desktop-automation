package com.project.www.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class PagesFactory {

    private static PagesFactory pagesFactory;
    private final WebDriver driver;
    private LoginPage loginPage;

    public PagesFactory(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public static PagesFactory getInstance() {
        return pagesFactory;
    }

    public static PagesFactory start(WebDriver driver) {
        pagesFactory = new PagesFactory(driver);
        return pagesFactory;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}
