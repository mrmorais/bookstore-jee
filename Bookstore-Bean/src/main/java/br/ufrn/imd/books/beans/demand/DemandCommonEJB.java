package br.ufrn.imd.books.beans.demand;

import java.util.List;

import br.ufrn.imd.books.entity.DemandEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * DemandCommonEJB
 */
public interface DemandCommonEJB {
  DemandEntity createNew(final DemandEntity demand) throws BookstoreUnknownException;
  DemandEntity attachIntentToOpenDemand(final Long intentId) throws BookstoreUnknownException;
  DemandEntity getOpenDemand();
  List<DemandEntity> getAll() throws BookstoreUnknownException;
}