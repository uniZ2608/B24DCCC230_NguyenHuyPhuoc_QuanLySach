package com.example.qlysach;
import java.io.Serializable;
public class Book implements Serializable {
    private String title;
    private String author;
    private String genre;
    private String status; // Chưa đọc, Đang đọc, Đã đọc
    private float rating;
    private String note;

    public Book(String title, String author, String genre, String status, float rating, String note) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.rating = rating;
        this.note = note;
    }

    // Tạo các Getter và Setter
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public float getRating() { return rating; }
    public String getNote() { return note; }
}
