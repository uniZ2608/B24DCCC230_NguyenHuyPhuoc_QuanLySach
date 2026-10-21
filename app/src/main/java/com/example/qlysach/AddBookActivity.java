package com.example.qlysach;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {
    EditText edtTitle, edtAuthor, edtNote;
    Spinner spinnerGenre;
    RadioGroup rgStatus;
    RatingBar rbRating;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtNote = findViewById(R.id.edtNote);
        spinnerGenre = findViewById(R.id.spinnerGenre);
        rgStatus = findViewById(R.id.rgStatus);
        rbRating = findViewById(R.id.rbRating);
        btnSave = findViewById(R.id.btnSave);

        // Thiết lập Spinner Thể loại
        String[] genres = {"Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genres);
        spinnerGenre.setAdapter(spinnerAdapter);

        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String author = edtAuthor.getText().toString().trim();
            String note = edtNote.getText().toString().trim();

            // Kiểm tra lỗi bỏ trống
            if (title.isEmpty()) {
                edtTitle.setError("Không được để trống");
                return;
            }
            if (author.isEmpty()) {
                edtAuthor.setError("Không được để trống");
                return;
            }

            String genre = spinnerGenre.getSelectedItem().toString();
            String status = "Chưa đọc";
            int selectedId = rgStatus.getCheckedRadioButtonId();
            if (selectedId == R.id.rbReading) status = "Đang đọc";
            else if (selectedId == R.id.rbRead) status = "Đã đọc";

            float rating = rbRating.getRating();

            // Thêm vào danh sách và tắt Activity
            Book newBook = new Book(title, author, genre, status, rating, note);
            MainActivity.bookList.add(newBook);
            finish(); // Trở về MainActivity
        });
    }
}