package com.example.textbookapp;

/**
 * Thrown when a user attempts to list a textbook that already exists
 * (same title + seller, case-insensitive).
 */
public class DuplicateBookException extends Exception {
    public DuplicateBookException(String title, String seller) {
        super("\"" + title + "\" by " + seller + " is already listed.");
    }
}
