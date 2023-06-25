package com.store.project.controller;

import com.store.project.exception.ProductValidationException;
import com.store.project.request.ProductRequest;
import com.store.project.response.ProductResponse;
import com.store.project.service.ProductService;
import com.store.project.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/stores/{storeId}/products")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable Long storeId,
                                                         @Valid @RequestBody ProductRequest productRequest,
                                                         BindingResult bindingResult) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.OK).body(productService.createProduct(storeId, productRequest));
    }

    @PutMapping("/stores/{storeId}/products/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long storeId,
                                                         @PathVariable Long productId,
                                                         @Valid @RequestBody ProductRequest productRequest,
                                                         BindingResult bindingResult) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProduct(storeId, productId, productRequest));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productId));
    }

    @GetMapping("/stores/{storeId}/products")
    public List<ProductResponse> getAllProductsByStore(@PathVariable Long storeId) {
        return productService.takeAllProductsForStore(storeId);
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProductsByStore() {
        return productService.takeAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ProductResponse getProduct(@PathVariable Long productId) {
        return productService.takeProductById(productId);
    }

    private void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = ErrorUtil.returnErrorMessage(bindingResult);
            throw new ProductValidationException(errorMessage);
        }
    }
}
