package com.store.project.controller;

import com.store.project.entity.Store;
import com.store.project.exception.ProductValidationException;
import com.store.project.request.StoreRequest;
import com.store.project.response.StoreResponse;
import com.store.project.service.StoreService;
import com.store.project.util.ErrorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<StoreResponse> getAllStores() {
        return storeService.takeAllStores();
    }

    @GetMapping("/{storeId}")
    public StoreResponse getStoreById(@PathVariable Long storeId) {
        return storeService.takeStoreById(storeId);
    }

    @PostMapping
    public ResponseEntity<StoreResponse> createStore(@Valid @RequestBody StoreRequest storeRequest,
                                                     BindingResult bindingResult) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.OK).body(storeService.createStore(storeRequest));
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponse> updateStore(@PathVariable Long storeId,
                                                     @Valid @RequestBody StoreRequest storeRequest,
                                                     BindingResult bindingResult) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.OK).body(storeService.updateStore(storeRequest, storeId));
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<StoreResponse> deleteStore(@PathVariable Long storeId) {
        return ResponseEntity.status(HttpStatus.OK).body(storeService.deleteStore(storeId));
    }

    private void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = ErrorUtil.returnErrorMessage(bindingResult);
            throw new ProductValidationException(errorMessage);
        }
    }
}
