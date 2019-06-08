package br.ufrn.imd.books.beans.book;

import javax.ejb.Remote;

import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * BookCommonEJB
 */
@Remote
public interface BookCommonEJB {
  BookEntity createNew(final BookEntity book) throws BookstoreUnknownException;
}