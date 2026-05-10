package com.example.textbookapp;

/**
 * Interface that any item which can be listed for sale must implement.
 * Satisfies the "Interfaces" requirement of the assignment.
 */
public interface Listable {
    String getTitle();
    String getSeller();
    double getPrice();
    int    getCopies();
    String getBankDetails();
}
