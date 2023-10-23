package catflix.wishlists;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@FeignClient(name = "products")
public interface ProductProxy {
    /**
     * Read all products
     *
     * @request GET /amazing
     * @response 200: returns all products
     */
    @GetMapping("/amazing")
    Iterable<Product> readAll();

    /**
     * Read a product
     *
     * @request GET /products/{id}
     * @response 404: product not found, 200: returns found product
     */
    @GetMapping("/products/{id}")
    Product readOne(@PathVariable int id);

    @PutMapping("/products/{id}")
    ResponseEntity<Product> updateOne(@PathVariable int id, @RequestBody Product product);

    @DeleteMapping("/products")
    void deleteAll();

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteOne(@PathVariable int id);

    @PostMapping
    ResponseEntity<Product> createOne(@RequestBody Product product);

}

