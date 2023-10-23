package catflix.wishlists;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//DAO
@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
  boolean existsByPseudoAndProductId(String pseudo, int productId);

  @Transactional
  void deleteByPseudoAndProductId(String pseudo, int productId);

  Iterable<Wishlist> findByPseudo(String pseudo);

  @Transactional
  void deleteByPseudo(String pseudo);


  Iterable<Wishlist> findAllByProductId(int productId);
}
