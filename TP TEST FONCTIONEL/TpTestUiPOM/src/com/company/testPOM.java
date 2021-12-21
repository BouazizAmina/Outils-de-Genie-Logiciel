package com.company;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class testPOM {
    static WebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        System.setProperty("webdriver.chrome.driver","C:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testEbay (){
        SearchPage searchPage = new SearchPage(driver,"267","python in easy steps");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        searchPage.search();

        SelectBook selectBook = new SelectBook(driver,"Python in easy steps by Mike McGrath 1840785969 The Fast Free Shipping");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        selectBook.select();

        Set<String> fenetres= driver.getWindowHandles();
        for(String fenetre : fenetres)  {
            if (!fenetre.equals(driver.getWindowHandle())){
                driver.switchTo().window(fenetre);
                break;
            }
        }

        CardPage cardPage = new CardPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        cardPage.addCard();

        EqualsTest equalsTest = new EqualsTest(driver,"https://cart.payments.ebay.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        equalsTest.testing();

    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {
        driver.quit();
    }
}
