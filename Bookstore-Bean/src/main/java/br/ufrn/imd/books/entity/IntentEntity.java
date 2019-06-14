package br.ufrn.imd.books.entity;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

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
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  /** Corresponding order */
  @JsonbTransient // Needed to avoid infinite loop
  @OneToOne
  private OrderEntity order;

  /** Intent type */
  private IntentType type;

  /** Empty constructor  */
  public IntentEntity() {
    // Intentionally left empty
  }

  /** Constructor */
  public IntentEntity(final OrderEntity order_, final IntentType intentType_) {
    this.order = order_;
    this.type = intentType_;
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
}