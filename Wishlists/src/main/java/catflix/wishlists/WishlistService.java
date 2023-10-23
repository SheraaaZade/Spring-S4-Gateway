package catflix.wishlists;

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

  private final WishlistRepository repository;
  private final ProductProxy productsProxy;

  public WishlistService(WishlistRepository repository, ProductProxy productsProxy) {
    this.repository = repository;
    this.productsProxy = productsProxy;
  }

  public boolean deleteProductFromWishlists(int product) {
    Iterable<Wishlist> wishlists = repository.findAllByProductId(product);
    if (wishlists == null) {
      return false;
    }

    for (Wishlist wishlist : wishlists) {
      repository.deleteByPseudoAndProductId(wishlist.getPseudo(), product);
    }
    return true;
  }

  public boolean deleteWishlist(String pseudo) {
    if (repository.findByPseudo(pseudo) == null) {
      return false;
    }
    repository.deleteByPseudo(pseudo);
    return true;
  }

  public Iterable<Wishlist> readAll(String pseudo) {
    return repository.findByPseudo(pseudo);
  }

  public boolean deletedOne(String pseudo, int product) {
    if (!repository.existsByPseudoAndProductId(pseudo, product)) {
      return false;
    }
    repository.deleteByPseudoAndProductId(pseudo, product);
    return true;
  }

  public boolean createOne(Wishlist wishlist) {
    repository.save(wishlist);
    return true;
  }

  public Iterable<Product> readFromUser(String pseudo) {
    Iterable<Wishlist> wishlists = repository.findByPseudo(pseudo);
    ArrayList<Product> products = new ArrayList<>();

    for (Wishlist wishlist : wishlists) {
      products.add(productsProxy.readOne(wishlist.getProductId()));
    }
    return products;
  }
}
