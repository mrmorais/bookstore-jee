package br.ufrn.imd.books.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.ufrn.imd.books.entity.RegistryEntity.RegistryType;
import br.ufrn.imd.books.exceptions.BookstoreValidationException;

import java.io.Serializable;
import java.util.Set;

@Entity(name="book")
@NamedQuery(name= "getAllBooks", query="SELECT b FROM book b")
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
   * Book registries
   */
  @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
  @JoinColumn(name="book_id")
  @JsonbTransient // Don't show this result
  private Set<RegistryEntity> registries;

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
            , final Double sellPrice, final Double costPrice)
            throws BookstoreValidationException {
    this.title = title;
    this.authorName = authorName;
    this.isbn = isbn;
    this.sellPrice = sellPrice;
    this.costPrice = costPrice;

    this.validateFields();
  }

  /**
   * Fields validation
   * @throws BookstoreValidationException
   */
  public void validateFields() throws BookstoreValidationException {
    if (this.title == null || this.title.isEmpty()) {
      throw new BookstoreValidationException("Informe o título do livro");
    }

    if (this.authorName == null || this.authorName.isEmpty()) {
      throw new BookstoreValidationException("Informe o nome do autor");
    }

    if (this.isbn == null || this.isbn.isEmpty()) {
      throw new BookstoreValidationException("Informe o ISBN");
    }

    if (this.costPrice == null) {
      throw new BookstoreValidationException("Informe o preço de custo");
    }

    if (this.sellPrice == null) {
      throw new BookstoreValidationException("Informe o preço de venda");
    }
  }

  public Set<RegistryEntity> getRegistries() {
    return this.registries;
  }

  private int calculateTotalQuantity(Set<RegistryEntity> registries) {
    int totalIn = registries.stream()
      .filter(r -> r.getType() == RegistryType.IN)
      .mapToInt(RegistryEntity::getQuantity)
      .sum();
    int totalOut = registries.stream()
      .filter(r -> r.getType() == RegistryType.OUT)
      .mapToInt(RegistryEntity::getQuantity)
      .sum();
    return totalIn - totalOut;
  }

  private int calculateAvailableQuantity(Set<RegistryEntity> registries) {
    int availableIn = registries.stream()
      .filter(r -> (r.getType() == RegistryType.IN && r.getVisible()))
      .mapToInt(RegistryEntity::getQuantity)
      .sum();
    int availableOut = registries.stream()
      .filter(r -> (r.getType() == RegistryType.OUT && r.getVisible()))
      .mapToInt(RegistryEntity::getQuantity)
      .sum();
    return availableIn - availableOut;
  }

  public void setRegistries(Set<RegistryEntity> registries) {
    this.registries = registries;
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

  public int getTotalQuantity() {
    if (this.registries == null) return 0;
    return calculateTotalQuantity(this.registries);
  }

  public int getAvailableQuantity() {
    if (this.registries == null) return 0;
    return calculateAvailableQuantity(this.registries);
  }
}