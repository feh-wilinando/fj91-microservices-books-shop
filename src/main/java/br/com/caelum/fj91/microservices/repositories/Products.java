package br.com.caelum.fj91.microservices.repositories;

import br.com.caelum.fj91.microservices.models.Product;
import br.com.caelum.fj91.microservices.rest.BooksClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Products {

    private final BooksClient client;

    public Products(BooksClient client) {
        this.client = client;
    }

    @Cacheable("products")
    public Product findById(Long id) {
        return client.getProductById(id);
    }

    public List<Product> findAll() {
        return client.getAllProducts();
    }
}
