package pl.tk.services;

import org.springframework.stereotype.Service;
import pl.tk.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MemoryProductService implements ProductsService {

    List<Product> products;

    public MemoryProductService() {
        products = new ArrayList<>();
        products.add(new Product(1, "Gray Shoe", "Gray Shoe description", "prod_2.png", 20.0));
        products.add(new Product(2, "Blue Shoe High Heels", "Blue Shoe High Heels description", "prod_3.png", 15.0));
        products.add(new Product(3, "Denim Jacket", "Denim Jacket description", "model_5.png", 5.5));
        products.add(new Product(4, "Leather Green Bag", "Leather Green Bag description", "prod_1.png", 50.0));
        products.add(new Product(5, "Smooth Cloth", "Smooth Cloth description", "model_1.png", 50.0));
        products.add(new Product(5, "Yellow Jacket", "Yellow Jacket description", "model_7.png", 50.0));
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return products.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Product> max = products.stream().max(Comparator.comparing(Product::getId));
        Integer newId = max.map(Product::getId).orElse(1);
        product.setId(newId);
        products.add(product);
        return product;
    }

    @Override
    public void updateProduct(String id, Product product) {
        Optional<Product> currentProduct = getProductById(id);
        currentProduct.ifPresent(current -> {
            current.setName(product.getName());
            current.setDescription(product.getDescription());
            current.setImage(product.getImage());
            current.setPrice(product.getPrice());
        });
    }

    @Override
    public boolean removeProduct(String id) {
        Long idAsLong = Long.parseLong(id);
        return products.removeIf(next -> idAsLong.equals(next.getId()));
    }
}
