package com.rikkeiacademy.demo_search_sort_pagination.controller;

import com.rikkeiacademy.demo_search_sort_pagination.entity.Product;
import com.rikkeiacademy.demo_search_sort_pagination.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String home(){
        return "home";
    }

    @GetMapping("/genData")
    public String genData(Model model){
        productService.genData(37);
        model.addAttribute("success","Generate data for products table successlly!");
        return "home";
    }

    @GetMapping("/listProductsWithPagination")
    public String listProductsWithPagination(@RequestParam(defaultValue = "")String proName,
                                             @RequestParam(defaultValue = "1")Integer page,
                                             @RequestParam(defaultValue = "10")Integer itemPage,
                                             @RequestParam(defaultValue = "")String orderBy,
                                             @RequestParam(defaultValue = "ASC")String sortBy,
                                             Model model){
        Page<Product> products = productService.getProductsWithPagination(proName, page - 1, itemPage, orderBy, sortBy);
        model.addAttribute("list",products.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("listPage",products.getTotalPages());
        model.addAttribute("proName",proName);
        model.addAttribute("orderBy",orderBy);
        model.addAttribute("sortBy",sortBy);
        return "listProducts";
    }
}
