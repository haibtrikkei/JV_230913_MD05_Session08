package com.rikkeiacademy.demo_search_sort_pagination.service.impl;

import com.rikkeiacademy.demo_search_sort_pagination.entity.Product;
import com.rikkeiacademy.demo_search_sort_pagination.repository.ProductRepository;
import com.rikkeiacademy.demo_search_sort_pagination.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public void genData(int totalProduct) {
        List<Product> list = new ArrayList<>();
        Random r = new Random();  // r.nextInt(n) -> sinh so ngau nhien tu 0 -> n-1
        int startYear = 2000;
        double startPrice = 100000;
        for (int i = 0; i < totalProduct; i++) {
            list.add(new Product(0,"Product name "+(i+1),"Producer "+(i+1),startYear+r.nextInt(25),startPrice+r.nextDouble(10000000)));
        }
        productRepository.saveAllAndFlush(list);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProductsWithPagination(String proName, Integer page, Integer itemPage, String orderBy, String sortBy) {
        Pageable pageable;
        List<Product> products;

        //xu ly co tim kiem hay khong?
        if(proName==null || proName.isEmpty()){
            //khong tim kiem theo proName
            products = productRepository.findAll();
        }else{
            //tim kiem theo proName
            products = productRepository.findProductsByProName(proName);
        }

        //xu ly viec sap xep va phan trang
        if(orderBy==null || orderBy.isEmpty()){
            pageable = PageRequest.of(page, itemPage);
        }else{
            if(sortBy.equals("ASC")){
                //tang dan
                pageable = PageRequest.of(page, itemPage, Sort.by(orderBy).ascending());
            }else{
                //giam dan
                pageable = PageRequest.of(page, itemPage, Sort.by(orderBy).descending());
            }
        }

        //xu ly lay ra danh sach du lieu cua trang tuong ung
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());
        int total = products.size();
        products = products.subList(start, end);
        return new PageImpl<>(products,pageable,total);
    }
}
