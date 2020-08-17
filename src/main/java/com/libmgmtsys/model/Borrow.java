package com.libmgmtsys.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "borrow_details")
public class Borrow implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  @Column(name = "borrow_id")
  private String borrow_id;  
  
  @Column(name = "book_id")
  private String book_id;
  
  @Column(name = "booking_date")
  private Date booking_date;

  @Override
  public String toString() {
    return "Borrow_Details [borrow_id=" + borrow_id + ", book_id=" + book_id + ", booking_date=" + booking_date
        + ", quantity=" + quantity + "]";
  }

  
  @Column(name = "quantity")
  private int quantity;

  public String getBorrow_id() {
    return borrow_id;
  }

  public void setBorrow_id(String borrow_id) {
    this.borrow_id = borrow_id;
  }

  public String getbook_id() {
    return book_id;
  }

  public void setbook_id(String book_id) {
    this.book_id = book_id;
  }

  public Date getBooking_date() {
    return booking_date;
  }

  public void setBooking_date(Date booking_date) {
    this.booking_date = booking_date;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

 

}
