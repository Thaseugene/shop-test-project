package com.store.project.mappers;

import com.store.project.entity.Store;
import com.store.project.request.ProductRequest;
import com.store.project.request.StoreRequest;
import com.store.project.response.StoreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreResponse mapToStoreResponse(Store store);

    @Mapping(target = "products", ignore = true)
    List<StoreResponse> mapToStoreResponseList(List<Store> products);
    Store mapToStore(StoreRequest StoreRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name")
    @Mapping(target = "address")
    @Mapping(target = "phone")
    @Mapping(target = "workingHours")
    Store mapToUpdateStore(StoreRequest StoreRequest, @MappingTarget Store store);
}
