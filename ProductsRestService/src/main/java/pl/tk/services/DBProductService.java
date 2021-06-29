package pl.tk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.tk.dao.ProductRepository;
import pl.tk.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class DBProductService implements ProductsService {

    private ProductRepository productRepository;

    public DBProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        Integer idAsLong = Integer.valueOf(id);
        return productRepository.findById(Integer.valueOf(id));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(String id, Product product) {
        productRepository.save(product);
    }

    @Override
    public boolean removeProduct(String id) {
        Product product = new Product();
        product.setId(Integer.valueOf(id));
        productRepository.delete(product);
        return true;
    }
}
