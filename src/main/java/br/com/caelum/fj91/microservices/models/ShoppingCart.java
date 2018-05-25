package br.com.caelum.fj91.microservices.models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Component
public class ShoppingCart implements Serializable {

    private Map<Product,ShoppingCartItem> items = new HashMap<>();

    public void add(Product product){
        items.putIfAbsent(product, new ShoppingCartItem(product));

        increase(product);
    }

    public void increase(Product product){
        items.computeIfPresent(product,( p , item) -> item.increase()  );
    }

    public void decrease(Product product){
        items.computeIfPresent(product, (p, item) -> item.decrease());
    }

    public boolean remove(Product product){
        return items.remove(product) != null;
    }

    public Collection<ShoppingCartItem> getItems(){
        return Collections.unmodifiableCollection(items.values());
    }

    public BigDecimal getTotal(){
        return items.values().stream().map(ShoppingCartItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
