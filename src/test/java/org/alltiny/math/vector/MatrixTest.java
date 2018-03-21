package org.alltiny.math.vector;

import junit.framework.Assert;
import org.junit.Test;

/**
 * This test ensures that {@link Matrix} is working correctly.
 */
public class MatrixTest {

    @Test(expected = IllegalArgumentException.class)
    public void testMatrixRejectsNull() {
        new Matrix((Vector[])null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatrixRejectsEmptyVectorArray() {
        new Matrix();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatrixRejectsNullVectors() {
        new Matrix(new Vector[]{null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatrixRejectsNullVectorsInArray() {
        new Matrix(new Vector(1), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatrixRejectsVectorsWithDifferentLength() {
        new Matrix(new Vector(1), new Vector(1, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMatrixRejectsVectorsWithZeroElements() {
        new Matrix(new Vector());
    }

    @Test
    public void testRowExtraction() {
        Matrix m = new Matrix(new Vector(1,2), new Vector(3,4));
        Assert.assertEquals("row should be", new Vector(1,2), m.getRow(0));
        Assert.assertEquals("row should be", new Vector(3,4), m.getRow(1));
    }

    @Test
    public void testColumnExtraction() {
        Matrix m = new Matrix(new Vector(1,2), new Vector(3,4));
        Assert.assertEquals("column should be", new Vector(1,3), m.getColumn(0));
        Assert.assertEquals("column should be", new Vector(2,4), m.getColumn(1));
    }

    @Test
    public void testSingleValueExtraction() {
        Matrix m = new Matrix(new Vector(1,2), new Vector(3,4));
        Assert.assertEquals("value on (0,0) should be", 1d, m.get(0,0));
        Assert.assertEquals("value on (0,1) should be", 2d, m.get(0,1));
        Assert.assertEquals("value on (1,0) should be", 3d, m.get(1,0));
        Assert.assertEquals("value on (1,0) should be", 4d, m.get(1,1));
    }

    @Test
    public void testAddingTwoMatrices() {
        Matrix m1 = new Matrix(new Vector(1,2), new Vector(3,4));
        Matrix m2 = new Matrix(new Vector(5,6), new Vector(7,8));
        Matrix m = m1.add(m2);
        Assert.assertEquals("value on (0,0) should be", 6d, m.get(0,0));
        Assert.assertEquals("value on (0,1) should be", 8d, m.get(0,1));
        Assert.assertEquals("value on (1,0) should be", 10d, m.get(1,0));
        Assert.assertEquals("value on (1,0) should be", 12d, m.get(1,1));
    }

    @Test(expected = IllegalDimensionException.class)
    public void testAddingMatricesWithDifferentRowDimensions() {
        new Matrix(new Vector(1,2), new Vector(3,4)).add(new Matrix(new Vector(5,6)));
    }

    @Test(expected = IllegalDimensionException.class)
    public void testAddingMatricesWithDifferentColumnDimensions() {
        new Matrix(new Vector(1,2), new Vector(3,4)).add(new Matrix(new Vector(5d), new Vector(6d)));
    }

    @Test
    public void testMultiplyingTwoMatrices() {
        Matrix a = new Matrix(new Vector(3,2,1), new Vector(1,0,2));
        Matrix b = new Matrix(new Vector(1,2), new Vector(0,1), new Vector(4,0));
        Matrix m = a.mul(b);
        Assert.assertEquals("value on (0,0) should be", 7d, m.get(0,0));
        Assert.assertEquals("value on (0,1) should be", 8d, m.get(0,1));
        Assert.assertEquals("value on (1,0) should be", 9d, m.get(1,0));
        Assert.assertEquals("value on (1,0) should be", 2d, m.get(1,1));
    }

    @Test(expected = IllegalDimensionException.class)
    public void testMultiplyingMatricesWithWrongDimensions() {
        Matrix a = new Matrix(new Vector(1,2,3), new Vector(4,5,6), new Vector(7,8,9));
        Matrix b = new Matrix(new Vector(1,2), new Vector(0,1));
        a.mul(b);
    }

    @Test
    public void testTransposingMatrix() {
        Matrix a = new Matrix(new Vector(3,2,1), new Vector(1,0,2));
        Matrix m = a.transpose();
        Assert.assertEquals("value a(0,0) should be m(0,0)", a.get(0,0), m.get(0,0));
        Assert.assertEquals("value a(0,1) should be m(1,0)", a.get(0,0), m.get(0,0));
        Assert.assertEquals("value a(0,2) should be m(2,0)", a.get(0,0), m.get(0,0));
        Assert.assertEquals("value a(1,0) should be m(0,1)", a.get(0,0), m.get(0,0));
        Assert.assertEquals("value a(1,1) should be m(1,1)", a.get(0,0), m.get(0,0));
        Assert.assertEquals("value a(1,2) should be m(2,1)", a.get(0,0), m.get(0,0));
    }

    @Test
    public void testMatricesWithPositiveZeroAndNegativeZeroEqual() {
        Assert.assertEquals("Matrices with positive zero and negative zero equal each other", new Matrix(new Vector(0,1)), new Matrix(new Vector(-0d,1)));
        Assert.assertTrue("Matrices with positive zero and negative zero produce the same hash", new Matrix(new Vector(0,1)).equals(new Matrix(new Vector(-0d,1))));
    }

    @Test
    public void testMatrixEqualsWithSameMatrix() {
        Matrix m = new Matrix(new Vector(1,5),new Vector(2,6));
        Assert.assertTrue("Matrices should equal itself", m.equals(m));
    }

    @Test
    public void testMatrixNotEqualsString() {
        Assert.assertFalse("Matrices should equal itself", new Matrix(new Vector(7,3),new Vector(8,4)).equals("foobar"));
    }

    @Test
    public void testMatricesWithZeroAndNegativeZeroCreateTheSameHash() {
        Assert.assertEquals("Matrices with positive zero and negative zero produce the same hash", new Matrix(new Vector(0,1)).hashCode(), new Matrix(new Vector(-0d,1)).hashCode());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("toString should be", "Matrix[Vector[7.0, 3.0], Vector[8.0, 4.0]]", new Matrix(new Vector(7,3),new Vector(8,4)).toString());
    }
}
