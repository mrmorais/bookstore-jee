package br.ufrn.imd.books.data;

import java.util.List;

import br.ufrn.imd.books.entity.DemandEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * DemandDao
 * @author Maradona Morais
 */
public interface DemandDao {
  public DemandEntity persist(DemandEntity demand) throws BookstoreUnknownException;
  public DemandEntity getOpenDemand();
  public DemandEntity findDemand(final Long demandId) throws BookstoreUnknownException;
  public List<DemandEntity> getAllDemands() throws BookstoreUnknownException;
}