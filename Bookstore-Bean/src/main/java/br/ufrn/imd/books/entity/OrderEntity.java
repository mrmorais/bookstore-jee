package br.ufrn.imd.books.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * OrderEntity
 */
@Entity(name="book_order")
@NamedQuery(name="getAllOrders", query="SELECT o FROM book_order o")
public class OrderEntity implements Serializable {
  
  private static final long serialVersionUID = -3988471087764836884L;

  /** Primary key */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_ORDER")
  @SequenceGenerator(name="SEQ_ORDER", sequenceName="seq_order", allocationSize=1)
  private Long id;

  /** Order creation date */
  private Date createdAt;

  @OneToMany(cascade=CascadeType.ALL, orphanRemoval= true, fetch=FetchType.EAGER)
  @JoinColumn(name="book_order_id")
  private Set<OrderItemEntity> items;

  @OneToOne(mappedBy="order")
  private IntentEntity intent;

  /** Empty constructor */
  public OrderEntity() { }

  /** Constructor */
  public OrderEntity(final Date createdAt) {
    this.createdAt = createdAt;
  }

  // @@@@ POJO methods @@@@

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
  public Set<OrderItemEntity> getItems() {
    return this.items;
  }

  /**
   * @param items items to set
   */
  public void setItems(Set<OrderItemEntity> items) {
    this.items = items;
  }

  public IntentEntity getIntent() {
    return this.intent;
  }

  public void setIntent(IntentEntity intent) {
    this.intent = intent;
  } 
}