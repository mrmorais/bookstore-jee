package br.ufrn.imd.books.exceptions;

/**
 * BookstoreUnknownException
 */
public class BookstoreUnknownException extends Exception {
  private static final long serialVersionUID = 1L;

  public BookstoreUnknownException() {
    super("Um erro não identificado ocorreu");
  }
}