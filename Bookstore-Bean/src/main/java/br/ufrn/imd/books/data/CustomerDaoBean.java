package br.ufrn.imd.books.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.ufrn.imd.books.entity.CustomerEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * CustomerDaoBean
 * @author Maradona Morais
 */
@Stateless
public class CustomerDaoBean implements CustomerDao {

  @PersistenceContext(unitName = "BookstorePU")
  private EntityManager entityManager;

  @Override
  public CustomerEntity persist(CustomerEntity customer) throws BookstoreUnknownException {
    try {
      entityManager.persist(customer);
      return customer;
    } catch (Exception e) {
      throw new BookstoreUnknownException();
    }
  }

  @Override
  public CustomerEntity findCustomer(Long customerId) throws BookstoreUnknownException {
    CustomerEntity customer = entityManager.find(CustomerEntity.class, customerId);
    if (customer == null) {
      throw new BookstoreUnknownException("Cliente não localizado.");
    }
    return customer;
  }

  @Override
  public List<CustomerEntity> getAll() throws BookstoreUnknownException {
    try {
      TypedQuery<CustomerEntity> query = entityManager
        .createNamedQuery("getAllCustomers", CustomerEntity.class);
      return query.getResultList();
    } catch (Exception e){
      throw new BookstoreUnknownException();
    }
  }
  
}