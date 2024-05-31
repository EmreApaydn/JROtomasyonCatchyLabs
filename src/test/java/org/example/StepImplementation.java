package org.example;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;


import driver.Driver;
import model.ElementInfo;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepImplementation extends Driver {



    public static String upload_Path = "";


    public StepImplementation() throws IOException {
        String currentWorkingDir = System.getProperty("user.dir");
        initMap(getFileList(currentWorkingDir + "/src"));
        //initMap(getFileList());
    }

    public WebElement findElementWithKey(String key){
        return findElement(key);
    }

    WebElement findElement(String key) {
        By infoParam = getElementInfoToBy(findElementInfoByKey(key));
        WebDriverWait webDriverWait = new WebDriverWait(driver,60);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    List<WebElement> findElements(String key) {
        return driver.findElements(getElementInfoToBy(findElementInfoByKey(key)));
    }

    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("name"))) {
            by = By.name(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("linkText")) {
            by = By.linkText(elementInfo.getValue());
        } else if (elementInfo.getType().equals(("partialLinkText"))) {
            by = By.partialLinkText(elementInfo.getValue());
        }
        return by;
    }



    private void clickElement(WebElement element) {
        element.click();
    }

    @Step("<Key> saniye kadar bekle")
    public void waitWithSecond(int Key) throws InterruptedException {
        Thread.sleep(Key * 1000);
    }

    @Step("Elementine tıkla <key>")
    public void clickElement(String key) {
        clickElement(findElement(key));
    }

    @Step("Su an ki URL <url> degerini iceriyor mu kontrol et")
    public void checkURLContainsRepeat (String expectedURL) {

    }

    @Step("<key> elementini kontrol et")
    public void checkElement(String key) {
        assertTrue(findElement(key).isDisplayed(), "Aranan element bulunamadı");
        System.out.println(key + " elementi bulundu.");
    }

    @Step("<key> elementi <text> degerini iceriyor mu kontrol et")
    public void checkElementContainsText(String key,String expectedText) {
        boolean containsText = findElement(key).getText().contains(expectedText);
        assertTrue(containsText, "Expected text is not contained");
        System.out.println(key + " elementi " + expectedText + " degerini iceriyor.");
    }
    @Step("<key> elementine kadar asagi kaydir")
    public void scrollWWithAction(String key){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(key)));
        actions.perform();
    }
    @Step({"<text> textini <key> elemente yaz"})
    public void sendKeys(String text, String key) {
        if (!key.equals("")) {
            findElement(key).sendKeys(text);
        }
    }
}


