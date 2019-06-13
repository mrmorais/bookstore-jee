package br.ufrn.imd.books.data;

import java.util.List;

import br.ufrn.imd.books.entity.BookEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

public interface BookDao {
  public BookEntity persist(BookEntity bookTitle) throws BookstoreUnknownException;
  public BookEntity findBook(Long bookId) throws BookstoreUnknownException;
  public List<BookEntity> getAll() throws BookstoreUnknownException;
}