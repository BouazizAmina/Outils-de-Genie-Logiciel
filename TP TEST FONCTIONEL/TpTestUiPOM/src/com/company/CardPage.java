package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardPage extends ObjectDriver{
    public CardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "isCartBtn_btn")
    WebElement addCard;

    public void addCard(){
        addCard.click();
    }
}
