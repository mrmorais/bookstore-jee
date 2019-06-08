package br.ufrn.imd.books.exceptions;

/**
 * BookstoreUnknownException
 */
public class BookstoreUnknownException extends Exception {
  private static final long serialVersionUID = -4749434144904730669L;

  public BookstoreUnknownException() {
    super("Um erro não identificado ocorreu");
  }
}