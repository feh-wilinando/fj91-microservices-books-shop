package br.com.caelum.fj91.microservices.dtos;

import br.com.caelum.fj91.microservices.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookDTO {
    private Long id;
    private String title;
    private List<String> authors = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public Product toEntity() {
        return new Product(id, title, authors, BigDecimal.TEN);
    }
}
