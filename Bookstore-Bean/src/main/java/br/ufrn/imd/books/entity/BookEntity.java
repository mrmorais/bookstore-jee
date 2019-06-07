package br.ufrn.imd.books.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Entity(name="Book")
@NamedQuery(name= "findAllBooks", query="SELECT e FROM Book e")
public class BookEntity implements Serializable {

  private static final long serialVersionUID = 7406410984594764224L;

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
   * Book sell price
   */
  private Double sellPrice;

  /**
   * Book cost price
   */
  private Double costPrice;

  /**
   * Empty constructor
   */
  public BookEntity() {}

  /**
   * Constructor
   */
  public BookEntity(final String title
            , final String authorName, final String isbn
            , final Double sellPrice, final Double costPrice) {
    this.title = title;
    this.authorName = authorName;
    this.isbn = isbn;
    this.sellPrice = sellPrice;
    this.costPrice = costPrice;
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
  public void setId(final long id) {
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
  public void setTitle(final String title) {
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
  public void setAuthorName(final String authorName) {
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
  public void setIsbn(final String isbn) {
    this.isbn = isbn;
  }

  /**
    * @return book cost price
    */
  public Double getCostPrice() {
    return this.costPrice;
  }

  /**
    * @param price price to set
    */
  public void setCostPrice(final Double costPrice) {
    this.costPrice = costPrice;
  }

  /**
    * @return book sell price 
    */
  public Double getSellPrice() {
    return this.sellPrice;
  }

  /**
    * @param price price to set
    */
  public void setSellPrice(final Double sellPrice) {
    this.sellPrice = sellPrice;
  }
}