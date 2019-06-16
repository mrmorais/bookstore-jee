package br.ufrn.imd.books.beans.customer;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.CustomerDao;
import br.ufrn.imd.books.entity.CustomerEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * CustomerBean
 */
@Stateless
public class CustomerBean implements CustomerLocalEJB, CustomerRemoteEJB {

  @EJB
  private CustomerDao customerDAO;

  @Override
  public CustomerEntity createNew(CustomerEntity customer) throws BookstoreUnknownException {
    return customerDAO.persist(customer);
  }

  @Override
  public List<CustomerEntity> getAll() throws BookstoreUnknownException {
    return customerDAO.getAll();
  }

}