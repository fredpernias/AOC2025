package com.example;

/**
 * Small utility helpers used across the examples.
 */
public final class Utils {
    private Utils() { /* utility class */ }

  

    /**
     * Left-pads the given string with the specified character so the result has length {@code length}.
     * If the input is {@code null}, it is treated as the empty string. If {@code length} is less than
     * or equal to the input length, the original string (or empty string if null) is returned.
     *
     * @param s the input string (may be null)
     * @param length desired total length of the resulting string
     * @param fillChar character to use for padding on the left
     * @return left-padded string of total length {@code length}
     */
    public static String padLeft(String s, int length, char fillChar) {
        String base = s == null ? "" : s;
        int current = base.length();
        if (length <= current) return base;
        int toAdd = length - current;
        return String.valueOf(fillChar).repeat(toAdd) + base;
    }
}
