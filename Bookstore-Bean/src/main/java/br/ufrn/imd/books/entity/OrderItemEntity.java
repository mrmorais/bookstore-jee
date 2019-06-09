package br.ufrn.imd.books.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * OrderItemEntity
 */
@Entity(name="order_item")
public class OrderItemEntity implements Serializable {
  private static final long serialVersionUID = -245753564203824983L;

  private static final String SEQ_ORDER_ITEM = "SEQ_ORDER_ITEM";

  /**
   * Primary key
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_ORDER_ITEM)
  @SequenceGenerator(name = SEQ_ORDER_ITEM, sequenceName="seq_order_item", allocationSize = 1)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name="book_order_id")
  private OrderEntity order;

  /**
   * Empty constructor
   */
  public OrderItemEntity() {
  }

  public OrderEntity getOrder() {
    return this.order;
  }

  public void setOrder(OrderEntity order_) {
    this.order = order_;
  }

}