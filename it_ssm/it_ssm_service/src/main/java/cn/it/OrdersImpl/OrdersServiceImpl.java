package cn.it.OrdersImpl;

import cn.it.Orders;
import cn.it.OrdersService;
import cn.it.dao.OrdersDao;
import com.github.pagehelper.PageHelper;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAllOrders(Integer page,Integer size) throws Exception {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(page,size);
        List<Orders> allOrders = ordersDao.findAllOrders();
        return allOrders;
    }

    @Override
    public Orders findById(String id) throws Exception{
        Orders byId = ordersDao.findById(id);
        return byId;
    }
}
