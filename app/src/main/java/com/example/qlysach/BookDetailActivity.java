package com.example.qlysach;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {
    TextView tvTitle, tvAuthor, tvStatus, tvNote;
    RatingBar rbRating;
    Button btnMarkRead, btnBack, btnDelete;
    int bookIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvAuthor = findViewById(R.id.tvDetailAuthor);
        tvStatus = findViewById(R.id.tvDetailStatus);
        tvNote = findViewById(R.id.tvDetailNote);
        rbRating = findViewById(R.id.rbDetailRating);
        btnMarkRead = findViewById(R.id.btnMarkRead);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        // Lấy index được truyền từ Intent
        bookIndex = getIntent().getIntExtra("BOOK_INDEX", -1);

        if (bookIndex != -1) {
            Book book = MainActivity.bookList.get(bookIndex);

            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor() + " • " + book.getGenre());
            tvStatus.setText(book.getStatus());
            rbRating.setRating(book.getRating());
            tvNote.setText(book.getNote().isEmpty() ? "Không có ghi chú" : book.getNote());

            // Vô hiệu hóa nút nếu sách đã ở trạng thái "Đã đọc"
            if (book.getStatus().equals("Đã đọc")) {
                btnMarkRead.setEnabled(false);
                btnMarkRead.setVisibility(View.GONE); // Hoặc ẩn đi tùy ý
            }

            // Nút Đánh dấu đã đọc
            btnMarkRead.setOnClickListener(v -> {
                MainActivity.bookList.get(bookIndex).setStatus("Đã đọc");
                finish(); // Đóng Activity và quay lại để update
            });

            // Nút Xóa
            btnDelete.setOnClickListener(v -> {
                MainActivity.bookList.remove(bookIndex);
                finish();
            });
        }

        // Nút Quay lại
        btnBack.setOnClickListener(v -> finish());
    }
}