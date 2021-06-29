package pl.tk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.tk.model.Product;
import pl.tk.services.ProductsService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

//consumes = MediaType.APPLICATION_JSON_VALUE
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsRestController {

    @Autowired
    ProductsService productsService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productsService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Optional<Product> productOp = productsService.getProductById(id);
        if(productOp.isPresent()) {
            return new ResponseEntity<>(productOp.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product savedProduct = productsService.addProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        return new ResponseEntity<>(savedProduct, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        boolean isRemoved = productsService.removeProduct(id);
        if (isRemoved) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product products, @PathVariable String id) {
        productsService.updateProduct(id, products);
        return ResponseEntity.ok().build();

    }
}
