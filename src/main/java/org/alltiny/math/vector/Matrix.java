package org.alltiny.math.vector;

import java.util.Arrays;

/**
 * This is a matrix.
 */
public class Matrix {

    private final Vector[] rows;
    private final int columns;

    public Matrix(Vector... rows) {
        if (rows.length == 0) {
            throw new IllegalDimensionException("matrix should have at least one row");
        }
        columns = rows[0].getDimension();
        if (columns == 0) {
            throw new IllegalDimensionException("matrix should have at least one column");
        }
        for (Vector row : rows) {
            if (columns != row.getDimension()) {
                throw new IllegalDimensionException("all row vectors must have identical dimensions");
            }
        }
        this.rows = rows;
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

    public Matrix mul(Matrix matrix) {
        if (getColumns() != matrix.getRows()) {
            throw new IllegalDimensionException("matrix with " + getColumns() + " column can not be multiplied to matrix with " + matrix.getRows() + " rows");
        }
        Vector[] result = new Vector[getRows()];
        for (int row = 0; row < getRows(); row++) {
            double[] rowVector = new double[matrix.getColumns()];
            for (int col = 0; col < matrix.getColumns(); col++) {
                rowVector[col] = getRow(row).scalar(matrix.getColumn(col));
            }
            result[row] = new Vector(rowVector);
        }
        return new Matrix(result);
    }

    public int getRows() {
        return rows.length;
    }

    public int getColumns() {
        return columns;
    }

    public Vector getRow(int index) {
        return rows[index];
    }

    public Vector getColumn(int index) {
        double[] column = new double[columns];
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
