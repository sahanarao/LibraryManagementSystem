package com.libmgmtsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {
  @Id
  @Column(name = "book_id")
  private String book_id;
  @Column(name = "title")
  private String title;
  @Column(name = "publisher")
  private String publisher;
  @Column(name = "available")
  private int available;

  @Override
  public String toString() {
    return "Book [book_id=" + book_id + ", title=" + title + ", publisher="
        + publisher + ", available=" + available + "]";
  }

  public Book(String book_id, String title,  String publisher, int available) {
    super();
    this.book_id = book_id;
    this.title = title;
    this.publisher = publisher;
    this.available = available;
  }

  public String getbook_id() {
    return book_id;
  }

  public void setbook_id(String book_id) {
    this.book_id = book_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public int getAvailable() {
    return available;
  }

  public void setAvailable(int available) {
    this.available = available;
  }

  public Book() {
    super();
  }

}
