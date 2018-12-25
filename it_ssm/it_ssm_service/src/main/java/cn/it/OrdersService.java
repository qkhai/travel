package cn.it;

import java.util.List;

public interface OrdersService {

    List<Orders> findAllOrders(Integer page,Integer size) throws Exception;

    Orders findById(String id) throws Exception;
}
