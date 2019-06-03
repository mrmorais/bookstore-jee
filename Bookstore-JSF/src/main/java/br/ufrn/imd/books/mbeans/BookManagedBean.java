package br.ufrn.imd.books.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class BookManagedBean {
    
    private String title;
    private String authorName;
    private String isbn;
    private Double price;

    public BookManagedBean() {}

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