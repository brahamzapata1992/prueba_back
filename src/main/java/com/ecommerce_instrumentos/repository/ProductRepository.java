package com.ecommerce_instrumentos.repository;

import com.ecommerce_instrumentos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNameContaining(String title);

    List<Product> findByCategory_IdIn(List<Long> categoryIds);

}
