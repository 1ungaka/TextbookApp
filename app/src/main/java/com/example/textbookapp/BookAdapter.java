package com.example.textbookapp;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    public interface OnBookClickListener {
        void onBookClick(Textbook book);
    }

    private List<Textbook>      books;
    private OnBookClickListener listener;

    public BookAdapter(List<Textbook> books, OnBookClickListener listener) {
        this.books    = books;
        this.listener = listener;
    }

    public void updateData(List<Textbook> newBooks) {
        this.books = newBooks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Textbook book = books.get(position);
        holder.tvTitle  .setText(book.getTitle());
        holder.tvSeller .setText("Seller: " + book.getSeller());
        holder.tvPrice  .setText(String.format(Locale.getDefault(), "R %.2f", book.getPrice()));
        holder.tvCopies .setText(book.getCopies() + (book.getCopies() == 1 ? " copy" : " copies"));
        holder.itemView .setOnClickListener(v -> listener.onBookClick(book));
    }

    @Override
    public int getItemCount() { return books.size(); }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSeller, tvPrice, tvCopies;
        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle  = itemView.findViewById(R.id.tv_book_title);
            tvSeller = itemView.findViewById(R.id.tv_book_seller);
            tvPrice  = itemView.findViewById(R.id.tv_book_price);
            tvCopies = itemView.findViewById(R.id.tv_book_copies);
        }
    }
}
