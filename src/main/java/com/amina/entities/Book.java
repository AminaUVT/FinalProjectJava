package com.amina.entities;

public class Book  extends Entity {

    private int book_id;
    private String author;
    private String title;
    private String price;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Book(" +
                "ID=%s," +
                "title=%s," +
                "author=%s," +
                "price=%s" +
                ")", book_id, title, author);
    }
}
