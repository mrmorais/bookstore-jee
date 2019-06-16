package br.ufrn.imd.books.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufrn.imd.books.entity.DemandEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * DemandDaoBean
 * 
 * @author Maradona Morais
 */
@Stateless
public class DemandDaoBean implements DemandDao {

  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  @Override
  public DemandEntity persist(DemandEntity demand) throws BookstoreUnknownException {
    try {
      entityManager.persist(demand);
      return demand;
    } catch (Exception e) {
      throw new BookstoreUnknownException();
    }
  }

  @Override
  public DemandEntity getOpenDemand() {
    try {
      TypedQuery<DemandEntity> query = entityManager
        .createQuery("FROM demand WHERE status = 0", DemandEntity.class);
      return query.getResultList().get(0);
    } catch(Exception e) {
      return null;
    }
  }

  @Override
  public List<DemandEntity> getAllDemands() throws BookstoreUnknownException {
    try {
      TypedQuery<DemandEntity> query = entityManager.createNamedQuery("getAllDemands", DemandEntity.class);
      return query.getResultList();
    } catch (Exception e){
      throw new BookstoreUnknownException();
    }
  }

  @Override
  public DemandEntity findDemand(Long demandId) throws BookstoreUnknownException {
    DemandEntity demand = entityManager.find(DemandEntity.class, demandId);
    if (demand == null) {
      throw new BookstoreUnknownException("Demanda não localizada.");
    }
    return demand;
  }

  
}