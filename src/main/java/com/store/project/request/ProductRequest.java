package com.store.project.request;

import com.store.project.entity.Store;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Should not me empty")
    @Size(max = 100)
    private String name;

    @Positive
    private double price;

    @Positive
    private int quantity;

    @NotBlank(message = "Should not me empty")
    @Size(max = 255)
    private String description;

}