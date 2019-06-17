package br.ufrn.imd.books.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * OrderItemEntity
 */
@Entity(name="order_item")
public class OrderItemEntity implements Serializable {
  private static final long serialVersionUID = -245753564203824983L;

  /**
   * Primary key
   */
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ORDER_ITEM")
  @SequenceGenerator(name="SEQ_ORDER_ITEM", sequenceName="seq_order_item", allocationSize=1)
  private Long id;

  @ManyToOne
  private BookEntity book;

  private int quantity;

  /**
   * Empty constructor
   */
  public OrderItemEntity() {
  }

  public OrderItemEntity(final BookEntity book, final int quantity) {
    this.book = book;
    this.quantity = quantity;
  }

  public long getId() {
    return this.id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BookEntity getBook() {
    return this.book;
  }

  public void setBook(BookEntity book) {
    this.book = book;
  }

}