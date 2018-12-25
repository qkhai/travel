package cn.it.controller;

import cn.it.Orders;
import cn.it.OrdersImpl.OrdersServiceImpl;
import cn.it.OrdersService;
import cn.it.Product;
import cn.it.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                          @RequestParam(name = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {
        List<Orders> allOrders = ordersService.findAllOrders(page,pageSize);
        PageInfo pageInfo = new PageInfo(allOrders);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("findById")
    public ModelAndView findById(String id) throws Exception{
        Orders orders = ordersService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orders-show");
        mv.addObject("orders",orders);
        return mv;
    }
}
