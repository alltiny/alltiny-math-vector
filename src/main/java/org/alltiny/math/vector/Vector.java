package org.alltiny.math.vector;

import java.util.Arrays;

/**
 * This vector class represents vectors of any dimension.
 * It provides common vector operations.
 */
public class Vector {

    private final double[] values;

    public Vector(double... values) {
        this.values = values;
    }

    /** Copy-Constructor */
    public Vector(Vector vector) {
        this.values = vector.values;
    }

    /** Creates a 0-vector with the given dimension. */
    public Vector(final int dimension) {
        values = new double[dimension];
    }

    public double get(int index) {
        return values[index];
    }

    /**
     * This method modifies the value of the given index.
     */
    public Vector set(int index, double value) {
        values[index] = value;
        return this;
    }

    /**
     * @return the dimension of this vector.
     */
    public int getDimension() {
        return values.length;
    }

    /**
     * This method calculates the square of the length of this vector.
     * Hint: This method is a bit faster then {@link #getLength()}.
     * @return the square of the length of this vector.
     */
    public double getLengthSquare() {
        double result = 0;
        for (double element : values) {
            result += element * element;
        }
        return result;
    }

    /**
     * This method calculates the length of this vector.
     * @return length of this vector.
     */
    public double getLength() {
        return Math.sqrt(getLengthSquare());
    }

    /**
     * @return a vector pointing into the same direction like this vector but with length of 1 (called normalized vector)
     */
    public Vector normalize() {
        final double length = getLength();
        Vector normalized = new Vector(getDimension());
        for (int i = 0; i < getDimension(); i++) {
            normalized.set(i, values[i] / length);
        }
        return normalized;
    }

    /**
     * This method adds the given vector to this vector and returns the resulting vector.
     * This method does not change this vector. Both vectors must have the same dimensions.
     * @param other to add
     * @return added vector of this vector and the given vector
     * @throws IllegalDimensionException if this and the given vector have unequal dimensions.
     */
    public Vector add(Vector other) {
        if (getDimension() != other.getDimension()) {
            throw new IllegalDimensionException("both vectors must have the same dimension");
        }
        Vector added = new Vector(getDimension());
        for (int i = 0; i < getDimension(); i++) {
            added.set(i, values[i] + other.get(i));
        }
        return added;
    }

    /**
     * This method subtracts the given vector from this vector and returns the resulting vector.
     * This method does not change this vector. Both vectors must have the same dimensions.
     * @param other to sub from this one
     * @return subtracted vector
     * @throws IllegalDimensionException if this and the given vector have unequal dimensions.
     */
    public Vector sub(Vector other) {
        if (getDimension() != other.getDimension()) {
            throw new IllegalDimensionException("both vectors must have the same dimension");
        }
        Vector sub = new Vector(getDimension());
        for (int i = 0; i < getDimension(); i++) {
            sub.set(i, values[i] - other.get(i));
        }
        return sub;
    }

    /**
     * Multiplies this vector with the given scalar.
     * @param scalar the multiply this vector with.
     * @return the scaled vector
     */
    public Vector mul(double scalar) {
        Vector vector = new Vector(getDimension());
        for (int i = 0; i < getDimension(); i++) {
            vector.set(i, values[i] * scalar);
        }
        return vector;
    }

    /**
     * Calculates the scalar product. Both vectors must have the same dimensions.
     * @param vector to create the scalar product with
     * @return scalar product of this vector and the given vector
     * @throws IllegalDimensionException if this and the given vector have unequal dimensions.
     */
    public double scalar(Vector vector) {
        if (getDimension() != vector.getDimension()) {
            throw new IllegalDimensionException("both vectors must have the same dimension");
        }
        double scalar = 0;
        for (int i = 0; i < getDimension(); i++) {
            scalar += get(i) * vector.get(i);
        }
        return scalar;
    }

    /**
     * This method calculates the cross product between this vector and the
     * given vector and returns the resulting vector. This method will not
     * change this vector. Note that the cross product is only defined for
     * three dimensional vectors (resp. in R3).
     * @param vector to create the cross product with
     * @return scalar product of this vector and the given vector
     * @throws IllegalDimensionException if this or the given vector has another dimension than 3.
     */
    public Vector cross(Vector vector) {
        if (getDimension() != 3 || vector.getDimension() != 3) {
            throw new IllegalDimensionException("both vectors must have 3 dimensions");
        }
        return new Vector(
            get(1) * vector.get(2) - get(2) * vector.get(1),
            get(2) * vector.get(0) - get(0) * vector.get(2),
            get(0) * vector.get(1) - get(1) * vector.get(0)
        );
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vector)) {
            return false;
        }

        Vector vector = (Vector)o;
        return Arrays.equals(values, vector.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
        return "Vector" + Arrays.toString(values);
    }
}
