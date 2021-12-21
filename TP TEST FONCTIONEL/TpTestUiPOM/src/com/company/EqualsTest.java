package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;

public class EqualsTest extends ObjectDriver{
    private String url;
    public EqualsTest(WebDriver driver, String url) {
        super(driver);
        this.url= url;
    }

    @FindBy(css = ".val-col.total-row ")
    WebElement selectTotal;

    @FindBy(id = "gh-cart-n")
    WebElement selectNbCard;

    public void testing(){
        assertEquals(driver.getCurrentUrl(), this.url);
        assertEquals(selectTotal.getText(), "US $12.14");
        assertEquals(selectNbCard.getText() , "1");
    }
}
