package com.example.gateway;

import com.example.gateway.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

  private final GatewayService gatewayService;

  public GatewayController(GatewayService gatewayService) {
    this.gatewayService = gatewayService;
  }

  @GetMapping("/products")
  public Iterable<Product> readAllProducts() {
    return gatewayService.readAllProducts();
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Product> readOneProduct(@PathVariable int id) {
    Product product = gatewayService.readOneProduct(id);

    if (product == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(product, HttpStatus.OK);
  }
}
