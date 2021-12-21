package com.company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ObjectDriver {
    protected WebDriver driver;

    public ObjectDriver(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}

