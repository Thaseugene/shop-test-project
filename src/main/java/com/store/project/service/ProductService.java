package com.store.project.service;

import com.store.project.request.ProductRequest;
import com.store.project.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(Long storeId, ProductRequest productRequest);

    ProductResponse takeProductById(Long productId);

    List<ProductResponse> takeAllProductsForStore(Long storeId);
    List<ProductResponse> takeAllProducts();

    ProductResponse updateProduct(Long storeId, Long productId, ProductRequest productRequest);
    ProductResponse deleteProduct(Long productId);

}
