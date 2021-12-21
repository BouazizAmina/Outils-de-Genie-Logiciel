package com.test;

import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixMathematicsTest {
    @Test
    public void determinantSize1() throws NoSquareException {
        double [][] tab= {{1.,2.,3},{2.,4.,3},{2.,0,0}};
        Assert.assertEquals(new MatrixMathematics().determinant(new Matrix(tab)),-12,0);
    }
    @Test
    public void determinantSizeSup2() throws NoSquareException {
        double [][] tab= {{1.}};
        Assert.assertEquals(new MatrixMathematics().determinant(new Matrix(tab)),1,0);
    }
    @Test(expected = NoSquareException.class)
    public void determinantException() throws NoSquareException {
        double [][] tab= {{1.,2.},{2.,4.},{1.,2.}};
        new MatrixMathematics().determinant(new Matrix(tab));
    }

}