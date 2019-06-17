package br.ufrn.imd.books.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * CustomerEntity
 */
@Entity(name="customer")
@NamedQuery(name= "getAllCustomers", query="SELECT c FROM customer c")
public class CustomerEntity implements Serializable {

  private static final long serialVersionUID = 1165323977556358668L;

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CUSTOMER")
  @SequenceGenerator(name="SEQ_CUSTOMER", sequenceName="seq_customer", allocationSize=1)
  /** primary key */
  private Long id;

  /** customer name */
  private String name;

  /** customer contact */
  private String contact;
  
  /** empty constructor */
  public CustomerEntity() {}

  /** Customer constructor */
  public CustomerEntity(final String name, final String contact) {
    this.name = name;
    this.contact = contact;
  }

  // @@@@ POJO methods @@@@

  public Long getId() {
    return this.id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getContact() {
    return this.contact;
  }
  public void setContact(String contact) {
    this.contact = contact;
  }

}