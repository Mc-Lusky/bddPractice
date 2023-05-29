package utils;

import base.basePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

public class generalUtils extends basePage {

    public static void navigateToBaseURL(){
        driverManager.getDriver().get(baseURL);
    }

    public static void isVisible(By element, String elementName, long timeOut){
        WebElement ele = driverManager.getDriver().findElement(element);
        WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.visibilityOf(ele));
        Reporter.log(elementName + " is visible", true);
    }
}
