package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SearchPage extends ObjectDriver{
    private String selectCat ;
    private String bookTitle ;

    @FindBy(id = "gh-cat")
    WebElement select;

    @FindBy(id = "gh-ac")
    WebElement inputSearch;

    public SearchPage(WebDriver driver, String selectCat, String bookTitle ) {
        super(driver);
        this.selectCat = selectCat;
        this.bookTitle = bookTitle;
    }

    public void search(){
        driver.get("https://www.ebay.com");
        Select sel = new Select(select);
        sel.selectByValue(this.selectCat);
        inputSearch.sendKeys(this.bookTitle);
        inputSearch.submit();
    }


}
