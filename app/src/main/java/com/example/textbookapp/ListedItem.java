package com.example.textbookapp;

/**
 * Abstract base class for items listed for sale.
 * Satisfies the "Abstract Classes" requirement of the assignment.
 */
public abstract class ListedItem implements Listable {

    protected String title;
    protected String seller;
    protected int    copies;
    protected double price;
    protected String bankDetails;

    public ListedItem(String title, String seller, int copies, double price, String bankDetails) {
        this.title       = title;
        this.seller      = seller;
        this.copies      = copies;
        this.price       = price;
        this.bankDetails = bankDetails;
    }

    /** Returns a formatted summary string — subclasses may override for richer output. */
    public abstract String getSummary();

    // ── Listable implementation ──────────────────────────────────────────────
    @Override public String getTitle()       { return title; }
    @Override public String getSeller()      { return seller; }
    @Override public double getPrice()       { return price; }
    @Override public int    getCopies()      { return copies; }
    @Override public String getBankDetails() { return bankDetails; }
}
