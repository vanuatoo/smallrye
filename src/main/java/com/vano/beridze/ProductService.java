package com.vano.beridze;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author vberidze
 */
@ApplicationScoped
public class ProductService {

    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Playstation"));
        products.add(new Product(2L, "Laptop"));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(Long productId) {
        try {
            return products.stream()
                    .filter(product -> productId.equals(product.getId()))
                    .findAny()
                    .orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public void createProduct(Product product) {
        products.add(new Product(Long.valueOf(products.get(products.size() - 1).getId().intValue()+ 1), product.getName()));
    }

    public void updateProduct(Long productId, Product product) {
        Product dbProduct = getProduct(productId);
        dbProduct.setName(product.getName());
    }

    public void deleteProduct(Long productId) {
        if (!products.removeIf(product -> productId.equals(product.getId()))) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

}
