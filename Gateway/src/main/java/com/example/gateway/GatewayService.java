package com.example.gateway;

import com.example.gateway.data.AuthenticationProxy;
import com.example.gateway.data.ProductsProxy;
import com.example.gateway.data.UsersProxy;
import com.example.gateway.data.WishlistsProxy;
import com.example.gateway.models.Product;
import com.example.gateway.models.UnsafeCredentials;
import com.example.gateway.models.User;
import feign.FeignException;
import org.springframework.http.HttpStatus;
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

  public ResponseEntity<Product> readOneProduct(int id) {
    ResponseEntity<Product> res;
    System.out.println("readOneProduct");
    try {
      res = productsProxy.readOne(id);
    } catch (FeignException e) {
//      if (e.status() == 404) {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//      } else {
      System.out.println("readOneProduct throw");
        throw e;
     // }
    }

    return res;
  }

  public Iterable<Product> readAllProducts() {
    return productsProxy.readAll();
  }

  public ResponseEntity<Void> createOne(UnsafeCredentials unsafeCredentials, User user) {
    try {
      usersProxy.createOne(unsafeCredentials.getPseudo(), user);
    } catch (FeignException e) {
      if (e.status() == 400) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      } else if (e.status() == 409) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      } else {
        throw e;
      }
    }

    try {
      authenticationProxy.createUser(unsafeCredentials);
    } catch (FeignException e) {
      try {
        usersProxy.deleteOne(unsafeCredentials.getPseudo());
      } catch (FeignException ex) {
        if (e.status() == 404) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      }

      if (e.status() == 400) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      } else if (e.status() == 409) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
      } else {
        throw e;
      }
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
