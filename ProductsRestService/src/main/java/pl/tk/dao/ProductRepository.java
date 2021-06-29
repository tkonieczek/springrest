package pl.tk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tk.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
