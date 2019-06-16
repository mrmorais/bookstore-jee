package br.ufrn.imd.books.data;

import java.util.List;

import br.ufrn.imd.books.entity.CustomerEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * CustomerDao
 * @author Maradona Morais
 */
public interface CustomerDao {
  public CustomerEntity persist(CustomerEntity customer) throws BookstoreUnknownException;
  public CustomerEntity findCustomer(Long customerId) throws BookstoreUnknownException;
  public List<CustomerEntity> getAll() throws BookstoreUnknownException;
}