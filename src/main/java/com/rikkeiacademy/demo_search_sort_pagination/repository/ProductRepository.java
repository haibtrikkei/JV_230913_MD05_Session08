package com.rikkeiacademy.demo_search_sort_pagination.repository;

import com.rikkeiacademy.demo_search_sort_pagination.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer>, JpaRepository<Product, Integer> {
    List<Product> findProductsByProName(String proName);
}
