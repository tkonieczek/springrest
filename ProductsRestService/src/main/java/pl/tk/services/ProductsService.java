package pl.tk.services;

import pl.tk.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductsService {

    Optional<Product> getProductById(String id);

    List<Product> getProducts();

    Product addProduct(Product product);

    void updateProduct(String id, Product product);

    boolean removeProduct(String id);
}
