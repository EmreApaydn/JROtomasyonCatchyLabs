package org.example;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation extends Driver {

    public static WebDriver driver ;

    public StepImplementation(){
        driver = Driver.driver ;
    }

    @Step("<key> saniye kadar bekle")
    public void waitSecond(int Key) throws InterruptedException {
        Thread.sleep( Key + 1000);
    }
    @Step("Css li <element> elemente tıkla")
    public void clickElementByCss (String element) {
        driver.findElement(By.cssSelector(element)).click();
    }

    @Step("Cssli <element> elementi bul ve <key> yaz")
    public void sendKeysByCss (String element, String keys){
        driver.findElement(By.cssSelector(element)).sendKeys(keys);
    }


}
