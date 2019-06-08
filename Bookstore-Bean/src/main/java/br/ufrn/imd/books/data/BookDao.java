package br.ufrn.imd.books.data;

import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

public interface BookDao {
  public BookEntity addNew(BookEntity bookTitle) throws BookstoreUnknownException;
}