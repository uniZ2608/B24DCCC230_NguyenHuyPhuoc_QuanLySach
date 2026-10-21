package com.example.qlysach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }
        Book book = getItem(position);

        TextView tvTitle = convertView.findViewById(R.id.tvItemTitle);
        TextView tvAuthor = convertView.findViewById(R.id.tvItemAuthor);
        TextView tvGenre = convertView.findViewById(R.id.tvItemGenre);
        TextView tvStatus = convertView.findViewById(R.id.tvItemStatus);
        RatingBar rbRating = convertView.findViewById(R.id.rbItemRating);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText("Tác giả: " + book.getAuthor());
        tvGenre.setText(book.getGenre());
        tvStatus.setText(book.getStatus());
        rbRating.setRating(book.getRating());

        return convertView;
    }
}