package com.store.project.response;

import com.store.project.entity.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String address;

    @NotBlank
    @Size(max = 20)
    private String phone;

    @NotBlank
    @Size(max = 50)
    private String workingHours;

}
