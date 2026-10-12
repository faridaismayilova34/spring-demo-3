package org.example.springdemo3.repository;

import org.example.springdemo3.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Transactional
    @Modifying
    @Query("update ProductEntity p set p.isExpired = true where p.expireDate < :today and p.isExpired = false")
    int updateExpiredProductsStatus(@Param("today") LocalDate today);
}
