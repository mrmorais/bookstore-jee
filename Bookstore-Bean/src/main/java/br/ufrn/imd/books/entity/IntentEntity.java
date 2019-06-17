package br.ufrn.imd.books.entity;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * IntentEntity
 * @author Maradona Morais
 */
@Entity(name="intent")
@NamedQuery(name="getAllIntents", query="SELECT o FROM intent o")
public class IntentEntity implements Serializable {

  private static final long serialVersionUID = 9212105234626839519L;

  public static enum IntentType {
    TRANSACTION, RESERVATION;
  }

  /** Primary key */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_INTENT")
  @SequenceGenerator(name="SEQ_INTENT", sequenceName="seq_intent", allocationSize=1)
  private Long id;

  /** Corresponding order */
  @JsonbTransient // Needed to avoid infinite loop
  @OneToOne
  private OrderEntity order;

  /** Intent type */
  private IntentType type;

  /** Intent customer */
  @ManyToOne
  private CustomerEntity customer;

  /** Intent sell order */
  @OneToOne
  private SellOrderEntity sellOrder;

  /** Empty constructor  */
  public IntentEntity() {
    // Intentionally left empty
  }

  /** Constructor */
  public IntentEntity(
      final OrderEntity order
    , final CustomerEntity customer
    , final IntentType intentType
  ) {
    this.order = order;
    this.customer = customer;
    this.type = intentType;
  }

  // @@@@ POJO methods @@@@

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public OrderEntity getOrder() {
    return this.order;
  }

  public void setOrder(OrderEntity order) {
    this.order = order;
  }

  public IntentType getType() {
    return this.type;
  }

  public void setType(IntentType type) {
    this.type = type;
  }

  public CustomerEntity getCustomer() {
    return this.customer;
  }

  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }

  public SellOrderEntity getSellOrder() {
    return this.sellOrder;
  }

  public void setSellOrder(SellOrderEntity sellOrder) {
    this.sellOrder = sellOrder;
  }
}