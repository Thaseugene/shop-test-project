package com.store.project.service;

import com.store.project.entity.Store;
import com.store.project.request.StoreRequest;
import com.store.project.response.StoreResponse;

import java.util.List;

public interface StoreService {

    StoreResponse createStore(StoreRequest storeRequest);
    List<StoreResponse> takeAllStores();
    StoreResponse takeStoreById(Long storeId);
    Store takeById(Long storeId);
    StoreResponse updateStore(StoreRequest storeRequest, Long StoreId);
    StoreResponse deleteStore(Long storeId);
}
