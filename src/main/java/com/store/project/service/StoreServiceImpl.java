package com.store.project.service;

import com.store.project.entity.Store;
import com.store.project.exception.StoreAlreadyExistsException;
import com.store.project.exception.StoreNotFoundException;
import com.store.project.mappers.StoreMapper;
import com.store.project.repository.ProductRepository;
import com.store.project.repository.StoreRepository;
import com.store.project.request.StoreRequest;
import com.store.project.response.StoreResponse;
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
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository,
                            StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    @Transactional
    public StoreResponse createStore(StoreRequest storeRequest) {
        Store store = storeMapper.mapToStore(storeRequest);
        if (storeRepository.findByName(store.getName()) != null) {
            throw new StoreAlreadyExistsException("Store with this name already exists");
        } else {
            return storeMapper.mapToStoreResponse(storeRepository.save(store));
        }
    }

    @Override
    public List<StoreResponse> takeAllStores() {
        List<Store> stores = storeRepository.findAll();
        if (CollectionUtils.isEmpty(stores)) {
            throw new StoreNotFoundException("There are no stores");
        } else {
            return storeMapper.mapToStoreResponseList(stores);
        }

    }

    @Override
    public StoreResponse takeStoreById(Long storeId) {
        return storeMapper.mapToStoreResponse(takeById(storeId));
    }

    @Override
    public Store takeById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException(String.format("Store with id = %s not found", storeId)));
    }

    @Override
    @Transactional
    public StoreResponse updateStore(StoreRequest storeRequest,
                                     Long storeId) {
        Store notUpdatedStore = takeById(storeId);
        Store someExistingStore = storeRepository.findByName(storeRequest.getName());
        if (someExistingStore!= null && !Objects.equals(someExistingStore.getId(), storeId)) {
            throw new StoreAlreadyExistsException("Store with this name already exists");
        } else {
            Store updatedStore = storeMapper.mapToUpdateStore(storeRequest,notUpdatedStore);
            return storeMapper.mapToStoreResponse(storeRepository.save(updatedStore));
        }
    }

    @Override
    @Transactional
    public StoreResponse deleteStore(Long storeId) {
        Store store = takeById(storeId);
        storeRepository.deleteById(storeId);
        return storeMapper.mapToStoreResponse(store);
    }


}
