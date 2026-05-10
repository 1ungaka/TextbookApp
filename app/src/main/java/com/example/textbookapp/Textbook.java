package com.example.textbookapp;

public class Textbook {
    private String title;
    private String seller;
    private int copies;
    private double price;
    private String bankDetails;

    public Textbook(String title, String seller, int copies, double price, String bankDetails) {
        this.title = title;
        this.seller = seller;
        this.copies = copies;
        this.price = price;
        this.bankDetails = bankDetails;
    }

    public String getTitle()       { return title; }
    public String getSeller()      { return seller; }
    public int    getCopies()      { return copies; }
    public double getPrice()       { return price; }
    public String getBankDetails() { return bankDetails; }

    public void setCopies(int copies)   { this.copies = copies; }
    public void setPrice(double price)  { this.price  = price;  }

    /** Two books are duplicates when they share the same title AND seller (case-insensitive). */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Textbook)) return false;
        Textbook other = (Textbook) o;
        return title.equalsIgnoreCase(other.title) && seller.equalsIgnoreCase(other.seller);
    }

    @Override
    public int hashCode() {
        return (title.toLowerCase() + seller.toLowerCase()).hashCode();
    }

    @Override
    public String toString() {
        return title + " by " + seller;
    }
}
