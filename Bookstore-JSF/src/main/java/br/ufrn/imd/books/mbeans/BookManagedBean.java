package br.ufrn.imd.books.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class BookManagedBean {
    
    private String title;
    private String authorName;
    private String isbn;
    private Double sellPrice;
    private Double costPrice;

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