package br.com.caelum.fj91.microservices.forms;

import br.com.caelum.fj91.microservices.models.Product;
import br.com.caelum.fj91.microservices.repositories.Products;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductForm {
    @NotNull
    @Min(0)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product toEntity(Products products){
        return products.findById(id);
    }
}
