package org.example.springdemo3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springdemo3.dto.ProductDto;
import org.example.springdemo3.entity.ProductEntity;
import org.example.springdemo3.exception.NotFoundException;
import org.example.springdemo3.exception.ValidationException;
import org.example.springdemo3.mapper.ProductMapper;
import org.example.springdemo3.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public List<ProductDto> getAll() {
        log.info("Fetching all products");
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto getById(Long id) {
        log.info("Fetching product with id: {}", id);
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PRODUCT_NOT_FOUND"));
        return productMapper.toDto(entity);
    }


    public ProductDto create(ProductDto dto) {
        log.info("Creating product: {}", dto);

        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Validation error: Price must be greater than 0");
            throw new ValidationException("PRICE_MUST_BE_GREATER_THAN_ZERO");
        }

        if (dto.getExpireDate() == null || !dto.getExpireDate().isAfter(LocalDate.now())) {
            log.error("Validation error: Expire date must be in the future");
            throw new ValidationException("EXPIRE_DATE_MUST_BE_IN_FUTURE");
        }

        ProductEntity entity = productMapper.toEntity(dto);
        ProductEntity saved = productRepository.save(entity);
        return productMapper.toDto(saved);
    }

    public ProductDto update(Long id, ProductDto dto) {
        log.info("Updating product with id: {}", id);
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PRODUCT_NOT_FOUND_FOR_UPDATE"));

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setType(dto.getType());
        entity.setQuantity(dto.getQuantity());
        entity.setExpireDate(dto.getExpireDate());

        ProductEntity updated = productRepository.save(entity);
        return productMapper.toDto(updated);
    }

    public void delete(Long id) {
        log.info("Deleting product with id: {}", id);
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("PRODUCT_NOT_FOUND_FOR_DELETE");
        }
        productRepository.deleteById(id);
    }

    public void reserve(Long id, Integer quantity) {
        log.info("Reserving product with id: {}, quantity: {}", id, quantity);

        if (quantity == null || quantity <= 0) {
            log.error("Validation error: Reserve quantity must be greater than 0");
            throw new ValidationException("QUANTITY_MUST_BE_GREATER_THAN_ZERO");
        }

        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PRODUCT_NOT_FOUND_FOR_RESERVE"));

        entity.setQuantity(entity.getQuantity() - quantity);
        productRepository.save(entity);
    }
}