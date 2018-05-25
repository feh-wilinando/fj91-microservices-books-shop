package br.com.caelum.fj91.microservices.models;

import java.math.BigDecimal;
import java.util.Objects;

public class ShoppingCartItem {

    private Product product;
    private Integer quantity;


    ShoppingCartItem() { }

    public ShoppingCartItem(Product product) {
        this(product, null);
    }

    public ShoppingCartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal(){
        return BigDecimal.valueOf(quantity).multiply(product.getPrice());
    }

    public ShoppingCartItem increase(){

        if (Objects.isNull(quantity))
            return new ShoppingCartItem(product, 1);

        return new ShoppingCartItem(product, quantity + 1);
    }

    public ShoppingCartItem decrease(){
        if (Objects.nonNull(quantity) && quantity > 1)
            return new ShoppingCartItem(product, quantity - 1);
        return this;
    }
}
