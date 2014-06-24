package org.alltiny.math.vector;

import org.junit.Assert;
import org.junit.Test;

/**
 * This test ensures that {@link Vector} is working correctly.
 */
public class VectorTest {

    @Test
    public void testCreationOfNullVector() {
        Assert.assertEquals("null vector should have zero length", 0, new Vector(3).getLength(), 0.000001);
    }

    @Test
    public void testLengthCalculation() {
        Vector v = new Vector(1, 1);
        Assert.assertEquals("length of vector should be correctly calculated", Math.sqrt(2), v.getLength(), 0.000001);
    }

    @Test
    public void testNormalization() {
        Vector v = new Vector(3, 4, 5);
        Vector normalized = v.normalize();
        Assert.assertEquals("normalized vector should have a length of 1", 1d, normalized.getLength(), 0.000001);
    }

    @Test
    public void testScalarCalculation() {
        Assert.assertEquals("the scalar of two orthogonal vectors is 0", 0, new Vector(1, 1).scalar(new Vector(1, -1)), 0.000001);
    }

    @Test
    public void testCrossCalculation() {
        Vector a = new Vector(3, 0, 0);
        Vector b = new Vector(0, 2, 0);
        Vector x = a.cross(b);
        Assert.assertEquals(0, x.get(0), 0.000001);
        Assert.assertEquals(0, x.get(1), 0.000001);
        Assert.assertEquals(6, x.get(2), 0.000001);
    }

    @Test
    public void testAdditionCalculation() {
        Vector a = new Vector(3, 0, 0);
        Vector b = new Vector(0, 2, 0);
        Vector x = a.add(b);
        Assert.assertEquals(3, x.get(0), 0.000001);
        Assert.assertEquals(2, x.get(1), 0.000001);
        Assert.assertEquals(0, x.get(2), 0.000001);
    }

    @Test
    public void testSubtractionCalculation() {
        Vector a = new Vector(3, 0, 0);
        Vector b = new Vector(0, 2, 0);
        Vector x = a.sub(b);
        Assert.assertEquals(3, x.get(0), 0.000001);
        Assert.assertEquals(-2, x.get(1), 0.000001);
        Assert.assertEquals(0, x.get(2), 0.000001);
    }

    @Test
    public void testEquals() {
        Assert.assertEquals("both vectors should be equal", new Vector(0.0), new Vector(-0.0));
    }
}
