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
        Assert.assertEquals("equal vectors should have the same hash", new Vector(0.0).hashCode(), new Vector(-0.0).hashCode());
    }

    @Test(expected = IllegalDimensionException.class)
    public void testVectorsWithDifferentDimensionsCanNotBeAdded() {
        new Vector(1d).add(new Vector(2, 3));
    }

    @Test(expected = IllegalDimensionException.class)
    public void testVectorsWithDifferentDimensionsCanNotBeSubtracted() {
        new Vector(1d).sub(new Vector(2, 3));
    }

    @Test(expected = IllegalDimensionException.class)
    public void testVectorsWithDifferentDimensionsCanNotBeUsedForScalar() {
        new Vector(1d).scalar(new Vector(2, 3));
    }

    @Test(expected = IllegalDimensionException.class)
    public void testVectorRejectsCrossProductIfItHasNotThreeDimensions() {
        new Vector(1).cross(new Vector(4, 5, 6));
    }

    @Test(expected = IllegalDimensionException.class)
    public void testVectorRejectsCrossProductIfOtherVectorHasNotThreeDimensions() {
        new Vector(1, 2, 3).cross(new Vector(4));
    }

    @Test
    public void testScalingOfVector() {
        Vector a = new Vector(3, 2, -1).scale(2);
        Assert.assertEquals(6, a.get(0), 0.000001);
        Assert.assertEquals(4, a.get(1), 0.000001);
        Assert.assertEquals(-2, a.get(2), 0.000001);
    }

    @Test
    public void testMultiplicationOfVector() {
        Vector a = new Vector(-3, 5).mul(4);
        Assert.assertEquals(-12, a.get(0), 0.000001);
        Assert.assertEquals(20, a.get(1), 0.000001);
    }

    @Test
    public void testProject2DVectorOnOtherOne() {
        // project [1,1] onto [2,0]
        Vector p = new Vector(1, 1).projectOn(new Vector(2, 0));
        Assert.assertNotNull("projected vector should not be null", p);
        Assert.assertEquals("x coordinate should be", 1, p.get(0), 0.000001);
        Assert.assertEquals("y coordinate should be", 0, p.get(1), 0.000001);
    }

    @Test(expected = IllegalDimensionException.class)
    public void testProjectOnRequiresVectorsWithSameDimensions() {
        new Vector(1).projectOn(new Vector(2));
    }

    @Test
    public void testProject2DVector() {
        // project [1,1] onto [0,2]
        Vector p = new Vector(0, 2).project(new Vector(1, 1));
        Assert.assertNotNull("projected vector should not be null", p);
        Assert.assertEquals("x coordinate should be", 0, p.get(0), 0.000001);
        Assert.assertEquals("y coordinate should be", 1, p.get(1), 0.000001);
    }

    @Test(expected = IllegalDimensionException.class)
    public void testProjectRequiresVectorsWithSameDimensions() {
        new Vector(1).project(new Vector(2));
    }

    @Test
    public void testVectorsEqualEachOther1D() {
        Assert.assertTrue("both vectors should equal each other", new Vector(1d).equals(new Vector(1d)));
    }

    @Test
    public void testVectorsEqualEachOther12D() {
        Assert.assertTrue("both vectors should equal each other", new Vector(4,6).equals(new Vector(4,6)));
    }

    @Test
    public void testVectorsDontEqualEachOther1D() {
        Assert.assertFalse("both vectors should not equal each other", new Vector(56d).equals(new Vector(1d)));
    }

    @Test
    public void testVectorsDontEqualEachOther2D() {
        Assert.assertFalse("both vectors should not equal each other", new Vector(7,8).equals(new Vector(7,7)));
    }

    @Test
    public void testVectorsWithDifferentDimensionsDontEqualEachOther() {
        Assert.assertFalse("both vectors should not equal each other", new Vector(1,2).equals(new Vector(1d)));
    }

    @Test
    public void testVectorShouldNotEqualNull() {
        Assert.assertFalse("vector should not equal null", new Vector(84d).equals(null));
    }

    @Test
    public void testVectorInstancesEqualEachOther1() {
        Vector v = new Vector(42,76);
        Assert.assertTrue("both vectors should equal each other", v.equals(v));
    }

    @Test
    public void testVectorsWithZeroAndNegativeZeroCreateTheSameHash() {
        Assert.assertEquals("Vectors with positive zero and negative zero produce the same hash", new Vector(0,1).hashCode(), new Vector(-0d,1).hashCode());
    }

    @Test
    public void testToStringWithPositiveZero() {
        Assert.assertEquals("toString should be", "Vector[0.0]", new Vector(0d).toString());
    }

    @Test
    public void testToStringWithNegativeZero() {
        Assert.assertEquals("toString should be", "Vector[-0.0]", new Vector(-0d).toString());
    }

    @Test
    public void testCopyConstructor() {
        Vector original = new Vector(65,43,21);
        Vector copy = new Vector(original);
        Assert.assertNotSame(original, copy);
        Assert.assertEquals("Both vectors should be equal", original, copy);
    }

    @Test
    public void testCopyConstructorDoesNotReferenceOriginal() {
        Vector original = new Vector(9,8,7);
        Vector copy = new Vector(original);
        Assert.assertEquals("x value on copy should be", 9, copy.get(0), 0.000001);
        copy.set(0, 6); // alter the x on the copy
        Assert.assertEquals("x value on copy should be", 6, copy.get(0), 0.000001);
        Assert.assertEquals("x value on original should be unchanged", 9, original.get(0), 0.000001);
    }
}
