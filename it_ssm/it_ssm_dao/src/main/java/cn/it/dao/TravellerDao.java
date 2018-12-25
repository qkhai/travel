package cn.it.dao;

import cn.it.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {
    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{ordrsId})")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
