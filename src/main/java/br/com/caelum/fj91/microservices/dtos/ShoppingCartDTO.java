package br.com.caelum.fj91.microservices.dtos;

import br.com.caelum.fj91.microservices.models.ShoppingCart;
import br.com.caelum.fj91.microservices.models.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.Collection;

public class ShoppingCartDTO {

    private Collection<ShoppingCartItem> items;
    private BigDecimal total;


    ShoppingCartDTO() {}

    public ShoppingCartDTO(ShoppingCart cart) {
        this.items = cart.getItems();
        this.total = cart.getTotal();
    }

    public Collection<ShoppingCartItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
