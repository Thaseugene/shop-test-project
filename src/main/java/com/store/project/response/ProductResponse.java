package com.store.project.response;

import com.store.project.entity.Store;
import lombok.*;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Positive
    private double price;

    @Positive
    private int quantity;

    @NotBlank
    @Size(max = 255)
    private String description;

    private Long storeId;

}