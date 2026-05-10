package com.example.textbookapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddBookFragment extends Fragment {

    private TextInputLayout  tilTitle, tilSeller, tilCopies, tilPrice, tilBank;
    private TextInputEditText etTitle, etSeller, etCopies, etPrice, etBank;
    private Button            btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tilTitle  = view.findViewById(R.id.til_title);
        tilSeller = view.findViewById(R.id.til_seller);
        tilCopies = view.findViewById(R.id.til_copies);
        tilPrice  = view.findViewById(R.id.til_price);
        tilBank   = view.findViewById(R.id.til_bank);

        etTitle  = view.findViewById(R.id.et_title);
        etSeller = view.findViewById(R.id.et_seller);
        etCopies = view.findViewById(R.id.et_copies);
        etPrice  = view.findViewById(R.id.et_price);
        etBank   = view.findViewById(R.id.et_bank);

        btnAdd = view.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(v -> attemptAdd());
    }

    private void attemptAdd() {
        // Clear previous errors
        tilTitle.setError(null);
        tilSeller.setError(null);
        tilCopies.setError(null);
        tilPrice.setError(null);
        tilBank.setError(null);

        String titleStr  = etTitle .getText().toString().trim();
        String sellerStr = etSeller.getText().toString().trim();
        String copiesStr = etCopies.getText().toString().trim();
        String priceStr  = etPrice .getText().toString().trim();
        String bankStr   = etBank  .getText().toString().trim();

        boolean valid = true;

        if (TextUtils.isEmpty(titleStr))  { tilTitle .setError("Title is required");           valid = false; }
        if (TextUtils.isEmpty(sellerStr)) { tilSeller.setError("Seller name is required");      valid = false; }
        if (TextUtils.isEmpty(copiesStr)) { tilCopies.setError("Number of copies is required"); valid = false; }
        if (TextUtils.isEmpty(priceStr))  { tilPrice .setError("Price is required");            valid = false; }
        if (TextUtils.isEmpty(bankStr))   { tilBank  .setError("Banking details are required"); valid = false; }

        if (!valid) return;

        int    copies;
        double price;

        try {
            copies = Integer.parseInt(copiesStr);
            if (copies <= 0) { tilCopies.setError("Must be at least 1 copy"); return; }
        } catch (NumberFormatException e) {
            tilCopies.setError("Enter a whole number");
            return;
        }

        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) { tilPrice.setError("Price must be greater than 0"); return; }
        } catch (NumberFormatException e) {
            tilPrice.setError("Enter a valid amount (e.g. 299.99)");
            return;
        }

        try {
            Textbook book = new Textbook(titleStr, sellerStr, copies, price, bankStr);
            DataManager.getInstance().addBook(book);
            showSuccess(titleStr);
            clearFields();
        } catch (DuplicateBookException e) {
            tilTitle.setError(e.getMessage());
        }
    }

    private void showSuccess(String title) {
        Toast.makeText(getContext(),
                "✓ \"" + title + "\" listed successfully!", Toast.LENGTH_LONG).show();
    }

    private void clearFields() {
        etTitle .setText("");
        etSeller.setText("");
        etCopies.setText("");
        etPrice .setText("");
        etBank  .setText("");
        etTitle.requestFocus();
    }
}
