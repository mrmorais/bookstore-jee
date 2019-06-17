package br.ufrn.imd.books.entity;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;

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
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DEMAND")
  @SequenceGenerator(name="SEQ_DEMAND", sequenceName="seq_demand", allocationSize=1)
  /** Primary key */
  private Long id;

  /** Demand status */
  private DemandStatus status;

  /** Intents list */
  @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
  @JoinColumn(name="demand_id")
  private Set<IntentEntity> intents;

  /** Empty constructor */
  public DemandEntity() {}

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
  public Set<IntentEntity> getIntents() {
    return this.intents;
  }

  /**
   * set demand intents
   * @param intents intents to set
   */
  public void setIntents(Set<IntentEntity> intents) {
    this.intents = intents;
  }
}