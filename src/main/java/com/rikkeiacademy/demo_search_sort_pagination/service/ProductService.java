package com.rikkeiacademy.demo_search_sort_pagination.service;


import com.rikkeiacademy.demo_search_sort_pagination.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    void genData(int totalProduct);
    List<Product> getProducts();
    Page<Product> getProductsWithPagination(String proName, Integer page, Integer itemPage, String orderBy, String sortBy);
}
