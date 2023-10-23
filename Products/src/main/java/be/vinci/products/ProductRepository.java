package be.vinci.products;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//c'est le DAO
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
}
