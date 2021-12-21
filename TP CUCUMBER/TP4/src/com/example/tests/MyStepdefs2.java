package com.example.tests;

import com.example.model.Matrix;
import com.example.service.MatrixMathematics;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class MyStepdefs2 {
    List<List<Double>> rows;
    double[][] table ;
    Matrix result;
    Matrix matrix=new Matrix();
    Matrix m=new Matrix();

    @Given("I have this matrix to calculate its transpose")
    public void iHaveThisMatrixToCalculateItsTranspose(DataTable dataTable) {
        rows = dataTable.asLists(Double.class);
    }

    @When("I calculate its transpose")
    public void iCalculateItsTranspose() {
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
        result = MatrixMathematics.transpose(matrix);
    }

    @Then("The result must be")
    public void theResultMustBe(DataTable d) {
        rows = d.asLists(Double.class);
        int j=0;
        for (List<Double> row : rows) {
            for (int i=0;i<3;i++){
                Assert.assertEquals(result.getValues()[j][i],row.get(i),0);
            }
            j++;
        }
    }
}
