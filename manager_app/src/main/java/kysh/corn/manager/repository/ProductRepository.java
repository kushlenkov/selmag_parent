package kysh.corn.manager.repository;

import kysh.corn.manager.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);
}