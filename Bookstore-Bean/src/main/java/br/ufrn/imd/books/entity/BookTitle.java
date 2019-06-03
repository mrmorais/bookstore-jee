package br.ufrn.imd.books.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQuery(name= "findAllBooks", query="SELECT e FROM BookTitle e")
public class BookTitle {
  private static final String SEQ_BOOK = "SEQ_BOOK";

  /**
   * Primary key
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_BOOK)
  @SequenceGenerator(name = SEQ_BOOK, sequenceName = "seq_book", allocationSize = 1)
  private Long id;

  /**
   * Book title
   */
  private String title;

  /**
   * Book author's name
   */
  private String authorName;

  /**
   * Book ISBN code
   */
  private String isbn;

  /**
   * Book selling price
   */
  private Double price;

  /**
   * Empty constructor
   */
  public BookTitle() {}

  /**
   * Constructor
   */
  public BookTitle(final long id, final String title
            , final String authorName, final String isbn
            , final Double price) {
    this.id = id;
    this.title = title;
    this.authorName = authorName;
    this.isbn = isbn;
    this.price = price;
  }

  /**
   * @return book id
   */
  public long getId() {
    return this.id;
  }

  /**
   * @param id id to set
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return book title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * @param title title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return author name
   */
  public String getAuthorName() {
    return this.authorName;
  }

  /**
   * @param authorName author name to set
   */
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  /**
   * @return book isbn
   */
  public String getIsbn() {
    return this.isbn;
  }

  /**
   * @param isbn book isbn to set
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * @return book price
   */
  public Double getPrice() {
    return this.price;
  }

  /**
   * @param price price to set
   */
  public void setPrice(Double price) {
    this.price = price;
  }
}