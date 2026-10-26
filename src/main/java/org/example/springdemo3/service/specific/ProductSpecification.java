package org.example.springdemo3.service.specific;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.springdemo3.dto.ProductFilterDto;
import org.example.springdemo3.entity.ProductEntity;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@RequiredArgsConstructor
public class ProductSpecification {

    private final ProductFilterDto filterDto;

    public Specification<ProductEntity> toPredicate() {
        if (Objects.isNull(filterDto)) {
            return null;
        }

        return Specification.allOf(
                nameContains(filterDto.getName()),
                typeEquals(filterDto.getType()),
                priceGreaterThanOrEqualTo(filterDto.getMinPrice()),
                priceLessThanOrEqualTo(filterDto.getMaxPrice()),
                isExpiredEquals(filterDto.getIsExpired()),
                customerIdEquals(filterDto.getCustomerId()),
                manufactureDateGreaterThanOrEqualTo(filterDto.getManufactureDateFrom()),
                manufactureDateLessThanOrEqualTo(filterDto.getManufactureDateTo())
        );
    }

    private static Specification<ProductEntity> nameContains(String name) {
        return (root, query, cb) -> (name == null || name.isBlank())
                ? null
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<ProductEntity> typeEquals(String type) {
        return (root, query, cb) -> (type == null || type.isBlank())
                ? null
                : cb.equal(root.get("type"), type);
    }

    private static Specification<ProductEntity> priceGreaterThanOrEqualTo(BigDecimal minPrice) {
        return (root, query, cb) -> minPrice == null
                ? null
                : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<ProductEntity> priceLessThanOrEqualTo(BigDecimal maxPrice) {
        return (root, query, cb) -> maxPrice == null
                ? null
                : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<ProductEntity> isExpiredEquals(Boolean isExpired) {
        return (root, query, cb) -> isExpired == null
                ? null
                : cb.equal(root.get("isExpired"), isExpired);
    }

    private static Specification<ProductEntity> customerIdEquals(Long customerId) {
        return (root, query, cb) -> customerId == null
                ? null
                : cb.equal(root.get("customerId"), customerId);
    }

    private static Specification<ProductEntity> manufactureDateGreaterThanOrEqualTo(LocalDate fromDate) {
        return (root, query, cb) -> fromDate == null
                ? null
                : cb.greaterThanOrEqualTo(root.get("manufactureDate"), fromDate);
    }

    private static Specification<ProductEntity> manufactureDateLessThanOrEqualTo(LocalDate toDate) {
        return (root, query, cb) -> toDate == null
                ? null
                : cb.lessThanOrEqualTo(root.get("manufactureDate"), toDate);
    }
}