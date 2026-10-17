package org.example.springdemo3.mapper;

import org.example.springdemo3.dto.ProductDto;
import org.example.springdemo3.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(ProductEntity entity);

    ProductEntity toEntity(ProductDto dto);

    void updateEntity(ProductDto dto, @MappingTarget ProductEntity entity);
}
