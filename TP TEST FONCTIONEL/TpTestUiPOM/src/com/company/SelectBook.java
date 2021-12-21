package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectBook extends ObjectDriver{
    private String book ;
    public SelectBook(WebDriver driver, String book) {
        super(driver);
        this.book=book;
    }

    @FindBy(className = "s-item__title")
    List<WebElement> elements;

    public void select(){
        for(WebElement element : elements)  {
            if (element.getText().equals(this.book)){
                element.click();
                break;
            }
        }
    }
}
