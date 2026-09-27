package org.backend.smallecommerceapi.repository;

import org.backend.smallecommerceapi.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
        select p from Product p join fetch p.category
        where lower(p.name) like lower(concat('%', :name, '%'))
        order by p.createdAt desc
    """)
    List<Product> searchByName(String name);
}
