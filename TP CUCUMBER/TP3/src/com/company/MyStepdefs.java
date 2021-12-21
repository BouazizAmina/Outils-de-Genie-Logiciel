package com.company;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;

public class MyStepdefs {
    int result;
    @Given("I have a calculator")
    public void iHaveACalculator() {
    }

    @When("I add {int} and {int}")
    public void iAddNumAndNum(int a, int b) {
        result = a + b;
    }

    @Then("The result must be {int}")
    public void theResultMustBeResults(int res) {
        Assert.assertEquals(result, res);
    }

}
