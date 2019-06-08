package br.ufrn.imd.books.exceptions;

/**
 * BookstoreValidationException
 */
public class BookstoreValidationException extends Exception {
  private static final long serialVersionUID = -4475537047118744326L;

  public BookstoreValidationException(final String message_) {
    super(message_);
  }
}