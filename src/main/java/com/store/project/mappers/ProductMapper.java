package com.store.project.mappers;

import com.store.project.entity.Product;
import com.store.project.request.ProductRequest;
import com.store.project.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "quantity", source = "product.quantity")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "storeId", source = "product.store.id")
    ProductResponse mapToProductResponse(Product product);
    List<ProductResponse> mapToProductResponseList(List<Product> products);
    Product mapToProduct(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name")
    @Mapping(target = "price")
    @Mapping(target = "quantity")
    @Mapping(target = "description")
    Product mapToUpdateProduct(ProductRequest productRequest, @MappingTarget Product product);

}
