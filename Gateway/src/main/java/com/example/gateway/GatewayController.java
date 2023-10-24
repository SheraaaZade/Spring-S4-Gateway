package com.example.gateway;

import com.example.gateway.models.Product;
import com.example.gateway.models.UnsafeCredentials;
import com.example.gateway.models.User;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    System.out.println("readOneProduct");
    return gatewayService.readOneProduct(id);
  }

  @PostMapping("/users/{pseudo}")
  public ResponseEntity<Void> createOne(@PathVariable UnsafeCredentials unsafeCredentials,
      @RequestBody User user) {
    if (!Objects.equals(user.getPseudo(), unsafeCredentials.getPseudo())) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return gatewayService.createOne(unsafeCredentials, user);
  }
}
