package br.ufrn.imd.books.data;

import br.ufrn.imd.books.entity.BookEntity;

public interface BookDao {
  public BookEntity addNew(BookEntity bookTitle);
}