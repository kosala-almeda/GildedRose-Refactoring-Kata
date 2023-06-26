package com.gildedrose.util.validators;

/**
 * ParameterValidator is a utility class to validate parameters
 */
public final class ParameterValidator {

    // Not instantiable
    private ParameterValidator() {
    }

    /**
     * Validate that the parameter is not null
     * 
     * @param parameter     the parameter to validate
     * @param parameterName the name of the parameter
     */
    public static <T> void validateNotNull(T parameter, String parameterName) throws IllegalArgumentException {
        if (parameter == null) {
            throw new IllegalArgumentException(parameterName + " cannot be null");
        }
    }

    /**
     * Validate that the String parameter is not null or empty
     * 
     * @param parameter     the parameter to validate
     * @param parameterName the name of the parameter
     */
    public static void validateNotEmpty(String parameter, String parameterName) throws IllegalArgumentException {
        validateNotNull(parameter, parameterName);
        if (parameter.isEmpty()) {
            throw new IllegalArgumentException(parameterName + " cannot be empty");
        }
    }

    /**
     * Validate that the integer parameter is positive
     * 
     * @param parameter     the parameter to validate
     * @param parameterName the name of the parameter
     */
    public static void validatePositive(int parameter, String parameterName) throws IllegalArgumentException {
        if (parameter <= 0) {
            throw new IllegalArgumentException(parameterName + " cannot be negative or zero");
        }
    }

    /**
     * Validate that the collection parameter is not null or empty
     * 
     * @param parameter     the parameter to validate
     * @param parameterName the name of the parameter
     */
    public static <T> void validateNotEmpty(Iterable<T> parameter, String parameterName) throws IllegalArgumentException {
        validateNotNull(parameter, parameterName);
        // check for empty collection
        if (!parameter.iterator().hasNext()) {
            throw new IllegalArgumentException(parameterName + " cannot be empty");
        }
    }

    /**
     * Validate that the collection parameter does not contain null elements
     * 
     * @param parameter     the parameter to validate
     * @param parameterName the name of the parameter
     */
    public static <T> void validateNoNullElements(Iterable<T> parameter, String parameterName)
            throws IllegalArgumentException {
        validateNotNull(parameter, parameterName);
        // check for null elements
        for (T element : parameter) {
            validateNotNull(element, parameterName + "'s element");
        }
    }

    /**
     * Validate that the collection parameter is not null or empty and that it does
     * not contain null elements
     * 
     * @param parameter     the parameter to validate
     * @param parameterName the name of the parameter
     */
    public static void validateNotEmptyNoNullElements(Iterable<?> parameter, String parameterName)
            throws IllegalArgumentException {
        validateNotEmpty(parameter, parameterName);
        validateNoNullElements(parameter, parameterName);
    }

    /**
     * Validate that the array parameter is not null or empty
     * 
     * @param parameter     the parameter to validate
     * @param parameterName the name of the parameter
     */
    public static <T> void validateNotEmpty(T[] parameter, String parameterName) throws IllegalArgumentException {
        validateNotNull(parameter, parameterName);
        // check for empty array
        if (parameter.length == 0) {
            throw new IllegalArgumentException(parameterName + " cannot be empty");
        }
    }

    /**
     * Validate that the array parameter is not null or empty and that it does
     * not contain null elements
     * 
     * @param parameter     the parameter to validate
     * @param parameterName the name of the parameter
     */
    public static <T> void validateNotEmptyNoNullElements(T[] parameter, String parameterName)
            throws IllegalArgumentException {
        validateNotEmpty(parameter, parameterName);
        // check for null elements
        for (T element : parameter) {
            validateNotNull(element, parameterName + "'s element");
        }
    }

}
