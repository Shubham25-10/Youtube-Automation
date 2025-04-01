package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(Wrappers.class.getName());
    
    public Wrappers(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null.");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

     // Click an element
    public void click(By locator) {
        logger.info("Attempting to click element: " + locator.toString());
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        logger.info("Clicked successfully.");
    }

    // Send keys to an element
    public void sendKeys(By locator, String text) {
        logger.info("Sending keys to element: " + locator.toString() + " | Text: " + text);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        logger.info("Keys sent successfully.");
    }

    // Get text from an element
    public String getText(By locator) {
        logger.info("Fetching text from element: " + locator.toString());
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        logger.info("Retrieved text: " + text);
        return text;
    }

    // Wait for element visibility
    public void waitForVisibility(By locator) {
        logger.info("Waiting for visibility of element: " + locator.toString());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

     // Check if element is displayed
    public boolean isDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            boolean displayed = element.isDisplayed();
            logger.info("Element visibility: " + locator.toString() + " | Status: " + displayed);
            return displayed;
        } catch (TimeoutException e) {
            logger.warning("Element not visible: " + locator.toString());
            return false;
        }
    }

    // Scroll to element
    public void scrollToElement(By locator) {
        logger.info("Scrolling to element: " + locator.toString());
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info("Scrolled to element successfully.");
    }

}
