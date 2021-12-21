package com.company;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestWikipedia {

    static WebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.setProperty("webdriver.chrome.driver","C:/chromedriver.exe");
        driver = new ChromeDriver();
    }


    @Test
    public void testEbay (){
        driver.get("https://www.ebay.com");
        Select sel = new Select(driver.findElement(By.id("gh-cat")));
        sel.selectByValue("267");
        WebElement inputSearch = driver.findElement(By.id("gh-ac"));
        inputSearch.sendKeys("python in easy steps");
        inputSearch.submit();
        List<WebElement> elements =  driver.findElements(By.className("s-item__title"));
        for(WebElement element : elements)  {
            if (element.getText().equals("Python in easy steps by Mike McGrath 1840785969 The Fast Free Shipping")){
                element.click();
                break;
            }
        }

        Set<String> fenetres= driver.getWindowHandles();
        for(String fenetre : fenetres)  {
            if (!fenetre.equals(driver.getWindowHandle())){
                driver.switchTo().window(fenetre);
                break;
            }
        }
        WebElement addCard = driver.findElement(By.id("isCartBtn_btn"));
        addCard.click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        assertEquals(driver.getCurrentUrl(), "https://cart.payments.ebay.com/");
        assertEquals(driver.findElement(By.cssSelector(".val-col.total-row ")).getText(), "US $12.14");
        assertEquals(driver.findElement(By.id("gh-cart-n")).getText() , "1");

    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {
        driver.quit();
    }

}
