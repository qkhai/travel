package cn.it.dao;

import cn.it.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
            "cn.it.dao.ProductDao.findById"))
    })
    List<Orders> findAllOrders() throws Exception;

    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select =
                    "cn.it.dao.ProductDao.findById")),
            @Result(column = "id",property = "travellers",many = @Many(select =
                    "cn.it.dao.TravellerDao.findByOrdersId")),
            @Result(column = "memberId",property = "member",one = @One(select =
                    "cn.it.dao.MemberDao.findById")),
    })
    Orders findById(String id) throws Exception;
}
