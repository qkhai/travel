package cn.it.dao;

import cn.it.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductDao {

    @Select("select * from product")
    List<Product> findAllProduct() throws Exception;

    @Select("select * from product where id =#{id}")
    Product findById(String id) throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

}
