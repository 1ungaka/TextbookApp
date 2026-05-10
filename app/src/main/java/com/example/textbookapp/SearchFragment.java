package com.example.textbookapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class SearchFragment extends Fragment {

    private BookAdapter       adapter;
    private RecyclerView      recycler;
    private TextView          tvEmpty;
    private TextInputEditText etSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSearch = view.findViewById(R.id.et_search);
        recycler = view.findViewById(R.id.recycler_search);
        tvEmpty  = view.findViewById(R.id.tv_search_empty);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        // Show all books initially
        List<Textbook> all = DataManager.getInstance().getAllBooks();
        adapter = new BookAdapter(all, book -> {
            BookDetailBottomSheet.newInstance(book)
                    .show(getParentFragmentManager(), "BookDetail");
        });
        recycler.setAdapter(adapter);
        updateEmptyState(all);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count)    {}
            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().trim();
                List<Textbook> results = query.isEmpty()
                        ? DataManager.getInstance().getAllBooks()
                        : DataManager.getInstance().search(query);
                adapter.updateData(results);
                updateEmptyState(results);
            }
        });
    }

    private void updateEmptyState(List<Textbook> data) {
        if (data.isEmpty()) {
            recycler.setVisibility(View.GONE);
            tvEmpty .setVisibility(View.VISIBLE);
        } else {
            recycler.setVisibility(View.VISIBLE);
            tvEmpty .setVisibility(View.GONE);
        }
    }
}
