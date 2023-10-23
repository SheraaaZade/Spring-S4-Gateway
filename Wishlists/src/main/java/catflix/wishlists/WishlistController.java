package catflix.wishlists;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@Controller
public class WishlistController {

  private final WishlistService wishlistService;
  private final ProductProxy prod;

  public WishlistController(WishlistService wishlistService, ProductProxy pxy) {
    this.prod = pxy;
    this.wishlistService = wishlistService;
  }

  @PutMapping("/wishlists/{pseudo}/{product}")
  public ResponseEntity<Void> addProductToWishlist(@PathVariable String pseudo,
      @PathVariable int product) {
    if (pseudo == null || pseudo.isBlank()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Wishlist wishlist = new Wishlist();
    wishlist.setPseudo(pseudo);
    wishlist.setProductId(product);

    boolean created = wishlistService.createOne(wishlist);
    if (!created) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } else {
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
  }

  @DeleteMapping("/wishlists/{pseudo}/{product}")
  public ResponseEntity<Void> removeProductFromWishlist(@PathVariable String pseudo,
      @PathVariable int product) {
    if (pseudo == null || pseudo.isBlank()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    boolean deleted = wishlistService.deletedOne(pseudo, product);

    if (!deleted) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @GetMapping("/wishlists/{pseudo}")
  public ResponseEntity<Iterable<Product>> getWishlist(@PathVariable String pseudo) {
    Iterable<Product> wishlist = wishlistService.readFromUser(pseudo);
    return ResponseEntity.ok(wishlist);
//    if (pseudo == null || pseudo.isBlank()) {
//      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

  //  ArrayList<Product> result = new ArrayList<>();
  //  Iterable<Wishlist> wishlist= wishlistService.readAll(pseudo);


//    for (Wishlist w : wishlist) {
//      ResponseEntity<Product> rsp = prod.readOne(w.getProductId());
//      if (rsp.getStatusCode() != HttpStatus.OK) {
//        System.out.println("aaaaaah je meurs by M. Xavier");
//      }
//      result.add(rsp.getBody());
//    }
//    if (result.isEmpty()) {
//      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    } else {
//      return new ResponseEntity<>(result, HttpStatus.OK);
//    }
  }

  @DeleteMapping("/wishlists/user/{pseudo}")
  public ResponseEntity<Void> deleteWishlist(@PathVariable String pseudo) {
    if (pseudo == null || pseudo.isBlank()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    boolean deleted = wishlistService.deleteWishlist(pseudo);

    if (!deleted) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping("/wishlists/products/{product}")
  public ResponseEntity<Void> removeProductFromWishlist(@PathVariable int product) {
    if (product < 0) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    boolean deleted = wishlistService.deleteProductFromWishlists(product);
    if (!deleted) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }


}
