package br.ufrn.imd.books.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * DemandEntity
 * @author Maradona Morais
 */
@Entity(name="demand")
@NamedQuery(name="getAllDemands", query="SELECT o FROM demand o")
public class DemandEntity implements Serializable {

  private static final long serialVersionUID = -945534620253236926L;

  public static enum DemandStatus {
    OPEN, SENT, CONSOLIDATED;
  }

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  /** Primary key */
  private Long id;

  /** Demand status */
  private DemandStatus status;

  /** Intents list */
  @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
  @JoinColumn(name="demand_id")
  private List<IntentEntity> intents;

  /** Empty constructor */
  public DemandEntity() {
    this.intents = new ArrayList<IntentEntity>();
  }

  // @@@@ POJO methods @@@@

  /**
   * get demand id
   * @return id
   */
  public Long getId() {
    return this.id;
  }

  /**
   * set demand id
   * @param id id to set
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  /**
   * get demand status
   * @return status
   */
  public DemandStatus getStatus() {
    return this.status;
  }

  /**
   * set demand status
   * @param status status to set
   */
  public void setStatus(DemandStatus status) {
    this.status = status;
  }

  /**
   * get demand intents
   * @return intents
   */
  public List<IntentEntity> getIntents() {
    return this.intents;
  }

  /**
   * set demand intents
   * @param intents intents to set
   */
  public void setIntents(List<IntentEntity> intents) {
    this.intents = intents;
  }
}