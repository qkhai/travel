package cn.it.ProductImpl;

import cn.it.Orders;
import cn.it.Product;
import cn.it.dao.OrdersDao;
import cn.it.dao.ProductDao;
import cn.it.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    private OrdersDao ordersDao;

    @Override
    public List<Product> findAllProduct() throws Exception {
        List<Product> allProduct = productDao.findAllProduct();
        return allProduct;
    }

    @Override
    public Product findById(String id) throws Exception {
        Product p = productDao.findById(id);
        return p;
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
