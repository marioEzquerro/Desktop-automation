package com.agricultura.pages;

import com.agricultura.utils.MyFluentWait;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractPage {
    protected Wait<WebDriver> wait;
    private final WebDriver driver;

    AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new MyFluentWait<>(driver)
                .withTimeout(15, ChronoUnit.SECONDS)
                .pollingEvery(2, ChronoUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public abstract WebElement getPageLoadedTestElement();

    protected WebDriver getDriver() {
        return driver;
    }

    protected Wait<WebDriver> getWait() {
        return wait;
    }

    protected void setWait(Wait<WebDriver> wait) {
        this.wait = wait;
    }

    public void waitForPageLoad() {
        WebElement testElement = getPageLoadedTestElement();
        wait.until(ExpectedConditions.visibilityOf(testElement));
    }

    protected void moveTo(WebElement elem) {
        if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equals("firefox")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
        } else {
            Actions actions = new Actions(driver);
            actions.moveToElement(elem).build().perform();
        }
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void setText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    protected boolean isElementLoaded(WebElement elem) {
        try {
            return elem.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtener elemento por buscqueda mendiante xpath
     *
     * @param xPath selector xpath
     * @return WebElement
     */
    protected WebElement getElementByXPath(String xPath) {
        return getDriver().findElement(By.xpath(xPath));
    }

    /**
     * Obtener lista de elementos por buscqueda mendiante xpath
     *
     * @param xPath selector xpath
     * @return List<WebElement>
     */
    protected List<WebElement> getElementsByXPath(String xPath) {
        return getDriver().findElements(By.xpath(xPath));
    }

    /**
     * Realizar scroll en pantalla
     *
     * @param startX corrdenada x de inicio
     * @param startY coordenada y de inicio
     * @param endX   corrdenada x final
     * @param endY   coordenada y final
     */
    protected void scroll(int startX, int startY, int endX, int endY) {
        getDriver().manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        TouchAction action = new TouchAction((AndroidDriver) getDriver());

        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY)).release().perform();

    }

    /**
     * Encontrar elemento via xPath en pantalla mediante scrolls
     *
     * @param xPath sentencia xpath
     * @return WebElement
     */
    protected WebElement scrollIntoView(String xPath) {
        getDriver().manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        Dimension dimension = getDriver().manage().window().getSize();

        int x = dimension.width / 2;
        int start_y = dimension.height - 300;
        int end_y = 300;

        while (getElementsByXPath(xPath).size() == 0) {
            scroll(x, start_y, x, end_y);
        }

        return getElementsByXPath(xPath).get(0);
    }
}
