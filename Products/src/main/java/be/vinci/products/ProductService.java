package be.vinci.products;

import org.springframework.stereotype.Service;
//Viens entre les 2, gère tous le reste
@Service
public class ProductService {
    private final ProductRepository repository;


    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public boolean createOne(Product product) {
        if (repository.existsById(String.valueOf(product.getId())))
            return false;
        repository.save(product);
        return true;
    }

    public Product readOne(int id){
        return repository.findById(String.valueOf(id)).orElse(null);
    }

    public Iterable<Product> readAll(){
        return repository.findAll();
    }

    public Product updateOne(Product newProduct){
        Product oldProduct = readOne(newProduct.getId());
        if(oldProduct == null)
            return null;

        repository.save(newProduct);
        return oldProduct;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public Product deleteOne(int id){
        Product product = readOne(id);
        if(product == null)
            return null;
        repository.deleteById(String.valueOf(id));
        return product;
    }
}
