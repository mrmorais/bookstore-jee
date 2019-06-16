package br.ufrn.imd.books.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * RegistryEntity
 * @author Maradona Morais
 */
@Entity(name="registry")
public class RegistryEntity implements Serializable {

  private static final long serialVersionUID = 5930023398117749217L;

  public static enum RegistryType {
    IN, OUT;
  }

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  /** Primary key */
  private Long id;

  /** Registry type */
  private RegistryType type;

  /** Registry quantity */
  private int quantity;

  /** Registry visibility */
  private boolean visible;

  /** Empty constructor */
  public RegistryEntity() {}
  
  /** Constructor */
  public RegistryEntity(final RegistryType type, final int quantity, final boolean visible) {
    this.type = type;
    this.quantity = quantity;
    this.visible = visible;
  }

  // @@@@ POJO methods @@@@
  
  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public RegistryType getType() {
    return this.type;
  }

  public void setType(final RegistryType type) {
    this.type = type;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(final int quantity) {
    this.quantity = quantity;
  }

  public boolean getVisible() {
    return this.visible;
  }

  public void setVisible(final boolean visible) {
    this.visible = visible;
  }
}