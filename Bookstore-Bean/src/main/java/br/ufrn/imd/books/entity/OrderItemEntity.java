package br.ufrn.imd.books.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String valor;
  
  // @ManyToOne
  // @JoinColumn(name="book_order_id")
  // private OrderEntity order;

  /**
   * Empty constructor
   */
  public OrderItemEntity() {
  }

  public OrderItemEntity(final String valor) {
    this.valor = valor;
  }

  public long getId() {
    return this.id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public String getValor() {
    return this.valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  // public OrderEntity getOrder() {
  //   return this.order;
  // }

  // public void setOrder(OrderEntity order_) {
  //   this.order = order_;
  // }

}