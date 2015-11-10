package com.android.asynchttpclient;

import java.io.Serializable;

/**
 * Created by Song on 2015/10/12.
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String isbn;
    private String title;
    private String author;
    private String image;

    public Book(String isbn, String title, String author, String image) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
