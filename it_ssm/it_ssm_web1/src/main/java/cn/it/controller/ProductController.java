package cn.it.controller;

import cn.it.Product;
import cn.it.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //产品添加
    @RequestMapping("save")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll";
    }

    //查询全部产品
    @RequestMapping("findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAllProduct();
        mv.addObject("productList", ps);
        mv.setViewName("product-list");
        return mv;

    }
}
