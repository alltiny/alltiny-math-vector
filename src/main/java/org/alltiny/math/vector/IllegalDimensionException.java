package org.alltiny.math.vector;

/**
 * This exception will be thrown when a vector's or matrix's dimension did not match expectations.
 */
public class IllegalDimensionException extends IllegalArgumentException {

    public IllegalDimensionException(String s) {
        super(s);
    }

    public IllegalDimensionException(String message, Throwable cause) {
        super(message, cause);
    }
}
