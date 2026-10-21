package com.example.qlysach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Dùng static để lưu dữ liệu trên bộ nhớ khi ứng dụng đang chạy
    public static ArrayList<Book> bookList = new ArrayList<>();
    BookAdapter adapter;
    ListView lvBooks;
    TextView tvTotal, tvRead, tvUnread;
    Button btnAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBooks = findViewById(R.id.lvBooks);
        tvTotal = findViewById(R.id.tvTotal);
        tvRead = findViewById(R.id.tvRead);
        tvUnread = findViewById(R.id.tvUnread);
        btnAddBook = findViewById(R.id.btnAddBook);

        // Khởi tạo dữ liệu mẫu ban đầu nếu danh sách rỗng
        if (bookList.isEmpty()) {
            bookList.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", "Đang đọc", 4, ""));
            bookList.add(new Book("Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", "Đã đọc", 5, ""));
            bookList.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", "Chưa đọc", 0, ""));
        }

        adapter = new BookAdapter(this, bookList);
        lvBooks.setAdapter(adapter);

        // Nút thêm sách chuyển sang AddBookActivity
        btnAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        // Bấm vào 1 item chuyển sang BookDetailActivity và truyền vào index của cuốn sách
        lvBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("BOOK_INDEX", position); // Truyền vị trí
            startActivity(intent);
        });
    }

    // Hàm update thống kê
    private void updateStatistics() {
        int total = bookList.size();
        int read = 0;
        int unread = 0;
        for (Book b : bookList) {
            if (b.getStatus().equals("Đã đọc")) read++;
            else unread++;
        }
        tvTotal.setText("Tổng: " + total);
        tvRead.setText("Đã đọc: " + read);
        tvUnread.setText("Chưa đọc xong: " + unread);
    }

    // Khi quay lại MainActivity (từ Add hoặc Detail), cập nhật lại List và Thống kê
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        updateStatistics();
    }
}
