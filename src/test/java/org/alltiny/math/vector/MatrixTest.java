package org.alltiny.math.vector;

import junit.framework.Assert;
import org.junit.Test;

/**
 * This test ensures that {@link Matrix} is working correctly.
 */
public class MatrixTest {

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
}
