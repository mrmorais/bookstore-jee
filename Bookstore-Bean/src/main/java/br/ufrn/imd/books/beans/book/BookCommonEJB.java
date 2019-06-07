package br.ufrn.imd.books.beans.book;

import javax.ejb.Remote;

import br.ufrn.imd.books.entity.BookEntity;

/**
 * BookCommonEJB
 */
@Remote
public interface BookCommonEJB {
  BookEntity createNew(final BookEntity book);
}