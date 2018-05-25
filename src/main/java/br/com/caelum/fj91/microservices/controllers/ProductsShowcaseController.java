package br.com.caelum.fj91.microservices.controllers;

import br.com.caelum.fj91.microservices.models.Product;
import br.com.caelum.fj91.microservices.repositories.Products;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsShowcaseController {


    private final Products products;

    public ProductsShowcaseController(Products products) {
        this.products = products;
    }


    @GetMapping
    public List<Product> getAll(){
        return products.findAll();
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Long id){
        return products.findById(id);
    }


}
