package br.ufrn.imd.books.utils;

/**
 * ExceptionWrapper
 */
public class ExceptionWrapper {

  public String message;
  public Boolean error;

  public ExceptionWrapper(Exception exception) {
    this.message = exception.getMessage();
    this.error = true;
  }
}