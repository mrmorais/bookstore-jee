package br.ufrn.imd.books;

import javax.ejb.Stateless;

/**
 * BookstoreEJB
 */
@Stateless(name = "BookstoreEJB")
public class BookstoreEJB implements BookstoreLocal, BookstoreRemote {

  @Override
  public int add(int a, int b) {
    return a + b;
  }

}