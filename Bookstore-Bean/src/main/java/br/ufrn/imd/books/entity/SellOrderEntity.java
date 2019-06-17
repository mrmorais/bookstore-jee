package br.ufrn.imd.books.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * SellOrderEntity
 * @author Maradona Morais
 */
@Entity(name="sell_order")
public class SellOrderEntity {
  public static enum SellOrderType {
    OPEN, CANCELED, CONSOLIDATED;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_SELL_ORDER")
  @SequenceGenerator(name="SEQ_SELL_ORDER", sequenceName="seq_sell_order", allocationSize=1)
  /** Primary key */
  private Long id;

  /** Sell order type */
  private SellOrderType type;

  /** Empty constructor */
  public SellOrderEntity(final SellOrderType type) {
    this.type = type;
  }

  // @@@@ POJO methods @@@@

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public SellOrderType getType() {
    return this.type;
  }

  public void setType(final SellOrderType type) {
    this.type = type;
  }
}