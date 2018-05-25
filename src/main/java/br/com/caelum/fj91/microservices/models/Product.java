package br.com.caelum.fj91.microservices.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Product {

    private Long id;
    private String title;
    private List<String> authors;
    private BigDecimal price;

    public Product(Long id, String title, List<String> authors, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
