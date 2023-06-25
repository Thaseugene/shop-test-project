package com.store.project.service;

import com.store.project.entity.Product;
import com.store.project.exception.ProductAlreadyExistsException;
import com.store.project.exception.ProductNotFoundException;
import com.store.project.mappers.ProductMapper;
import com.store.project.repository.ProductRepository;
import com.store.project.request.ProductRequest;
import com.store.project.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreService storeService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              StoreService storeService,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.storeService = storeService;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductResponse createProduct(Long storeId,
                                         ProductRequest productRequest) {
        Product product = productMapper.mapToProduct(productRequest);
        product.setStore(storeService.takeById(storeId));
        if (productRepository.findByNameAndStoreId(product.getName(), storeId) != null) {
            throw new ProductAlreadyExistsException("Product with this name already exists");
        } else {
            return productMapper.mapToProductResponse(productRepository.save(product));
        }
    }

    @Override
    public ProductResponse takeProductById(Long productId) {
        return productMapper.mapToProductResponse(takeById(productId));
    }

    @Override
    public List<ProductResponse> takeAllProductsForStore(Long storeId) {
        List<Product> products = productRepository.findByStoreId(storeId);
        if (CollectionUtils.isEmpty(products)) {
            throw new ProductNotFoundException("There are no products");
        } else {
            return productMapper.mapToProductResponseList(products);
        }

    }

    @Override
    public List<ProductResponse> takeAllProducts() {
        List<Product> products = productRepository.findAll();
        if (CollectionUtils.isEmpty(products)) {
            throw new ProductNotFoundException("There are no products");
        } else {
            return productMapper.mapToProductResponseList(products);
        }
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long storeId,
                                         Long productId,
                                         ProductRequest productRequest) {
        {
            Product notUpdatedProduct = takeById(productId);
            Product someExistingProduct = productRepository.findByNameAndStoreId(productRequest.getName(), storeId);
            if (someExistingProduct != null && !Objects.equals(someExistingProduct.getId(), productId)) {
                throw new ProductAlreadyExistsException("Product with this name already exists");
            } else {
                Product updatedProduct = productMapper.mapToUpdateProduct(productRequest, notUpdatedProduct);
                return productMapper.mapToProductResponse(productRepository.save(updatedProduct));
            }
        }
    }

    @Override
    @Transactional
    public ProductResponse deleteProduct(Long productId) {
        Product product = takeById(productId);
        productRepository.delete(product);
        return productMapper.mapToProductResponse(product);
    }

    private Product takeById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(String.format("Product with id = %s not found", productId)));
    }
}
