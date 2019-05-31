package br.ufrn.imd.books;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ufrn.imd.books.BookstoreRemote;

/**
 * CalcBean
 */
@RequestScoped
@Named
public class CalcManagedBean {

  private int sum;

  @EJB
  private BookstoreRemote bsEJB;

  @PostConstruct
  public void init() {
    this.sum = bsEJB.add(5, 2);
  }

  /**
   * @return the sum
   */
  public int getSum() {
    return sum;
  }

  /**
   * @param sum the sum to set
   */
  public void setSum(int sum) {
    this.sum = sum;
  }
}