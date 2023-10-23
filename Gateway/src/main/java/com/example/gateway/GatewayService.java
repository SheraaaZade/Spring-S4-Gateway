package com.example.gateway;

import com.example.gateway.data.AuthenticationProxy;
import com.example.gateway.data.ProductsProxy;
import com.example.gateway.data.UsersProxy;
import com.example.gateway.data.WishlistsProxy;
import com.example.gateway.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {

  private final ProductsProxy productsProxy;
  private final UsersProxy usersProxy;
  private final WishlistsProxy wishlistsProxy;
  private final AuthenticationProxy authenticationProxy;

  public GatewayService(ProductsProxy productsProxy, UsersProxy usersProxy,
      WishlistsProxy wishlistsProxy, AuthenticationProxy authenticationProxy) {
    this.productsProxy = productsProxy;
    this.usersProxy = usersProxy;
    this.wishlistsProxy = wishlistsProxy;
    this.authenticationProxy = authenticationProxy;
  }

  public Product readOneProduct(int id) {
    ResponseEntity<Product> response = productsProxy.readOne(id);
    return response.getBody();
  }

  public Iterable<Product> readAllProducts() {
    return productsProxy.readAll();
  }
}
