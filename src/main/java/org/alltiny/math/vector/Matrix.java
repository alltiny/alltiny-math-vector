package org.alltiny.math.vector;

import java.util.Arrays;

/**
 * This is a matrix.
 */
public class Matrix {

    private final Vector[] rows;
    private final int columns;

    public Matrix(Vector... rows) {
        if (rows == null) {
            throw new IllegalArgumentException("vector rows can not be null");
        }
        if (rows.length == 0) {
            throw new IllegalDimensionException("matrix should have at least one row");
        }
        if (rows[0] == null) {
            throw new IllegalArgumentException("vectors can not be null");
        }

        int detectedColumns = 0;
        for (Vector row : rows) {
            if (row == null) {
                throw new IllegalArgumentException("vectors can not be null");
            }
            if (detectedColumns == 0) {
                detectedColumns = row.getDimension();
            } else if (detectedColumns != row.getDimension()) {
                throw new IllegalDimensionException("all row vectors must have identical dimensions");
            }
        }
        if (detectedColumns == 0) {
            throw new IllegalDimensionException("matrix should have at least one column");
        }
        columns = detectedColumns;
        this.rows = rows;
    }

    public Matrix add(Matrix matrix) {
        if (getRows() != matrix.getRows() || getColumns() != matrix.getColumns()) {
            throw new IllegalDimensionException("matrices must have same dimensions");
        }
        Vector[] result = new Vector[rows.length];
        for (int row = 0; row < rows.length; row++) {
            result[row] = rows[row].add(matrix.getRow(row));
        }
        return new Matrix(result);
    }

    public Vector mul(Vector vector) {
        if (columns != vector.getDimension()) {
            throw new IllegalDimensionException("vector with " + vector.getDimension() + " dimensions can not be multiplied with matrix with " + columns + " columns");
        }
        double[] result = new double[rows.length];
        for (int row = 0; row < rows.length; row++) {
            for (int col = 0; col < columns; col++) {
                result[row] += vector.get(col) * rows[row].get(col);
            }
        }
        return new Vector(result);
    }

    public Matrix mul(Matrix matrix) {
        if (getColumns() != matrix.getRows()) {
            throw new IllegalDimensionException("matrix with " + getColumns() + " columns can not be multiplied to matrix with " + matrix.getRows() + " rows");
        }
        Vector[] result = new Vector[getRows()];
        for (int row = 0; row < getRows(); row++) {
            final Vector rowVector = getRow(row);
            final double[] resultRowVector = new double[matrix.getColumns()];
            for (int col = 0; col < matrix.getColumns(); col++) {
                resultRowVector[col] = rowVector.scalar(matrix.getColumn(col));
            }
            result[row] = new Vector(resultRowVector);
        }
        return new Matrix(result);
    }

    public Matrix transpose() {
        Vector[] result = new Vector[getColumns()];
        for (int column = 0; column < getColumns(); column++) {
            result[column] = getColumn(column);
        }
        return new Matrix(result);
    }

    public int getRows() {
        return rows.length;
    }

    public int getColumns() {
        return columns;
    }

    public double get(final int row, final int column) {
        return getRow(row).get(column);
    }

    public Vector getRow(int index) {
        return rows[index];
    }

    public Vector getColumn(int index) {
        double[] column = new double[rows.length];
        for (int row = 0; row < rows.length; row++) {
            column[row] = rows[row].get(index);
        }
        return new Vector(column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matrix)) {
            return false;
        }

        Matrix matrix = (Matrix)o;
        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rows);
    }

    @Override
    public String toString() {
        return "Matrix" + Arrays.toString(rows);
    }
}
