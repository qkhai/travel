package cn.it;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct() throws Exception;

    Product findById(String id) throws Exception;

    void save(Product product);
}
