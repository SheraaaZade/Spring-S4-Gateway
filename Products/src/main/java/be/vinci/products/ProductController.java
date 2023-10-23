package be.vinci.products;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Gestion des https, il fait appel au service
@RestController

public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Read all products
     *
     * @request GET /amazing
     * @response 200: returns all products
     */
    @GetMapping("/amazing")
    public Iterable<Product> readAll() {
        return service.readAll();
    }

    /**
     * Read a product
     *
     * @request GET /products/{id}
     * @response 404: product not found, 200: returns found product
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> readOne(@PathVariable int id) {
        Product product = service.readOne(id);

        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateOne(@PathVariable int id, @RequestBody Product product) {
        if (product.getId() != id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Product oldProduct = service.updateOne(product);
        if (oldProduct == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(oldProduct, HttpStatus.OK);
    }


    @DeleteMapping("/products")
    public void deleteAll() {
        service.deleteAll();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteOne(@PathVariable int id) {
        Product product = service.deleteOne(id);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody Product product) {
        boolean created = service.createOne(product);
        if (!created)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else
            return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
