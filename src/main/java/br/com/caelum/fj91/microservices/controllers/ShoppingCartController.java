package br.com.caelum.fj91.microservices.controllers;

import br.com.caelum.fj91.microservices.dtos.ShoppingCartDTO;
import br.com.caelum.fj91.microservices.forms.ProductForm;
import br.com.caelum.fj91.microservices.models.Product;
import br.com.caelum.fj91.microservices.models.ShoppingCart;
import br.com.caelum.fj91.microservices.repositories.Products;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {

    private final ShoppingCart cart;
    private final Products products;

    public ShoppingCartController(ShoppingCart cart, Products products) {
        this.cart = cart;
        this.products = products;
    }


    @GetMapping
    public ShoppingCartDTO get(){
        return new ShoppingCartDTO(cart);
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductForm form){

        Product product = form.toEntity(products);

        cart.add(product);

        return ResponseEntity.accepted().body(new ShoppingCartDTO(cart));
    }

}
