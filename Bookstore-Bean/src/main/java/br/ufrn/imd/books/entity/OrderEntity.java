package br.ufrn.imd.books.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * OrderEntity
 */
@Entity(name="book_order")
@NamedQuery(name="getAllOrders", query="SELECT o FROM book_order o")
public class OrderEntity implements Serializable {
  
  private static final long serialVersionUID = -3988471087764836884L;

  /**
   * Primary key
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /**
   * Order creation date
   */
  private Date createdAt;

  @OneToMany(cascade=CascadeType.ALL, orphanRemoval= true)
  @JoinColumn(name="book_order_id")
  private List<OrderItemEntity> items;

  /**
   * Empty constructor
   */
  public OrderEntity() {
    this.items = new ArrayList<OrderItemEntity>();
  }

  /**
   * Constructor
   */
  public OrderEntity(final Date createdAt) {
    this.createdAt = createdAt;
    this.items = new ArrayList<OrderItemEntity>();
  }

  /**
   * @return order id
   */
  public long getId() {
    return this.id;
  }

  /**
   * @param id id to set
   */
  public void setId(final long id) {
    this.id = id;
  }

  /**
   * @return creation date
   */
  public Date getCreatedAt() {
    return this.createdAt;
  }

  /**
   * @param createdAt new creation date
   */
  public void setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * @return order items
   */
  public List<OrderItemEntity> getItems() {
    return this.items;
  }

  /**
   * @param items items to set
   */
  public void setItems(List<OrderItemEntity> items) {
    this.items = items;
  }
}