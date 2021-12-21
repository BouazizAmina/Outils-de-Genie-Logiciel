package com.company;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class MyStepdefs2 {
    List<List<String>> rows;
    int result=0;
    @Given("I have the following books in the store")
    public void iHaveTheFollowingBooksInTheStore(DataTable dataTable) {
        rows = dataTable.asLists(String.class);
    }

    @When("I search for books by author {string}")
    public void iSearchForBooksByAuthor(String a) {
        for (List<String> columns : rows) {
            if(a.equals(columns.get(1))){
                result++ ;
            }
        }
    }

    @Then("I find {int} books")
    public void iFindBooks(int b) {
        Assert.assertEquals(result,b);
    }
}
