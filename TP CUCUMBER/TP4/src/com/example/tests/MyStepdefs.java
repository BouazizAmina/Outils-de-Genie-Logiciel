package com.example.tests;

import com.example.exception.NoSquareException;
import com.example.model.Matrix;
import com.example.service.MatrixMathematics;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class MyStepdefs {
    List<List<Double>> rows;
    double[][] table ;
    double result;
    Matrix matrix=new Matrix();

    @Given("I have this matrix")
    public void iHaveThisMatrix(DataTable dataTable) {
        rows = dataTable.asLists(Double.class);
    }

    @When("I calculate the determinant")
    public void iCalculateTheDeterminant() {
        int j=0;
        for (List<Double> row : rows) {
            if (j==0){
                table = new double[rows.size()][row.size()];
            }
            for (int i=0; i<row.size();i++){
                table[j][i]=row.get(i);
            }
            j++;
        }
        matrix.setData(table);
        try {
            result = MatrixMathematics.determinant(matrix);
        }
        catch (NoSquareException ex){
        }
    }

    @Then("The result must be {int}")
    public void theResultMustBe(int a) {
        Assert.assertEquals(result,a,0);
    }
}
