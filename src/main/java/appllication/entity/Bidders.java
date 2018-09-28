package appllication.entity;

import javax.persistence.*;

@Entity
public class Bidders {

    @GeneratedValue
    @Id
    private long id;

    private String author;

    private long price;

    @ManyToOne
    private Auction auction;


    public Bidders(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
