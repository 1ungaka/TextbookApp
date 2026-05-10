package com.example.textbookapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton that manages the in-memory list of textbooks.
 * Provides add, remove, and search operations.
 */
public class DataManager {

    private static DataManager instance;
    private final List<Textbook> books = new ArrayList<>();

    private DataManager() {
        // Seed with sample data so Browse screen is not empty on first launch
        try {
            addBook(new Textbook("Data Structures & Algorithms", "Alice Mokoena", 3, 320.00, "FNB | Acc: 62012345678"));
            addBook(new Textbook("Operating Systems Concepts", "Brian Dube",     2, 280.00, "ABSA | Acc: 40987654321"));
            addBook(new Textbook("Computer Networks",          "Carol Nkosi",    1, 250.00, "Capitec | Acc: 1234567890"));
            addBook(new Textbook("Introduction to Java",       "David Sithole",  4, 195.00, "Nedbank | Acc: 1098765432"));
            addBook(new Textbook("Discrete Mathematics",       "Eve Mthembu",    2, 310.00, "Standard | Acc: 0011223344"));
        } catch (DuplicateBookException ignored) { /* seeds are unique */ }
    }

    public static DataManager getInstance() {
        if (instance == null) instance = new DataManager();
        return instance;
    }

    /** Adds a book; throws DuplicateBookException if the same title+seller already exists. */
    public void addBook(Textbook book) throws DuplicateBookException {
        for (Textbook b : books) {
            if (b.equals(book)) throw new DuplicateBookException(book.getTitle(), book.getSeller());
        }
        books.add(book);
    }

    /** Removes a book by reference. */
    public void removeBook(Textbook book) {
        books.remove(book);
    }

    /** Returns a copy of all books. */
    public List<Textbook> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Returns books whose title OR seller name contains the query (case-insensitive).
     */
    public List<Textbook> search(String query) {
        String q = query.trim().toLowerCase();
        List<Textbook> results = new ArrayList<>();
        for (Textbook b : books) {
            if (b.getTitle().toLowerCase().contains(q) ||
                b.getSeller().toLowerCase().contains(q)) {
                results.add(b);
            }
        }
        return results;
    }
}
