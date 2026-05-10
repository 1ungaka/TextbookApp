package com.example.textbookapp;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

public class BookDetailBottomSheet extends BottomSheetDialogFragment {

    private static final String ARG_TITLE  = "title";
    private static final String ARG_SELLER = "seller";
    private static final String ARG_COPIES = "copies";
    private static final String ARG_PRICE  = "price";
    private static final String ARG_BANK   = "bank";

    public static BookDetailBottomSheet newInstance(Textbook book) {
        BookDetailBottomSheet sheet = new BookDetailBottomSheet();
        Bundle args = new Bundle();
        args.putString("title",  book.getTitle());
        args.putString("seller", book.getSeller());
        args.putInt   ("copies", book.getCopies());
        args.putDouble("price",  book.getPrice());
        args.putString("bank",   book.getBankDetails());
        sheet.setArguments(args);
        return sheet;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_book_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args == null) return;

        String title  = args.getString("title",  "");
        String seller = args.getString("seller", "");
        int    copies = args.getInt   ("copies", 0);
        double price  = args.getDouble("price",  0.0);
        String bank   = args.getString("bank",   "");

        ((TextView) view.findViewById(R.id.bs_title)) .setText(title);
        ((TextView) view.findViewById(R.id.bs_seller)).setText("Seller: " + seller);
        ((TextView) view.findViewById(R.id.bs_price)) .setText(
                String.format(Locale.getDefault(), "R %.2f per copy", price));
        ((TextView) view.findViewById(R.id.bs_copies)).setText(
                copies + (copies == 1 ? " copy available" : " copies available"));
        ((TextView) view.findViewById(R.id.bs_bank))  .setText("Payment: " + bank);

        view.findViewById(R.id.bs_close).setOnClickListener(v -> dismiss());
    }
}
