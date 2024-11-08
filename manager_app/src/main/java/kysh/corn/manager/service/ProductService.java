package kysh.corn.manager.service;

import kysh.corn.manager.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product createProduct(String title, String details);
}
