package com.example.textbookapp;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BrowseFragment extends Fragment {

    private BookAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browse, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.recycler_books);
        TextView     empty    = view.findViewById(R.id.tv_empty);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Textbook> books = DataManager.getInstance().getAllBooks();

        if (books.isEmpty()) {
            recycler.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        } else {
            recycler.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
            adapter = new BookAdapter(books, book -> showBookDetail(book));
            recycler.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh list when returning from AddBook
        if (adapter != null) {
            adapter.updateData(DataManager.getInstance().getAllBooks());
        }
    }

    private void showBookDetail(Textbook book) {
        BookDetailBottomSheet sheet = BookDetailBottomSheet.newInstance(book);
        sheet.show(getParentFragmentManager(), "BookDetail");
    }
}
