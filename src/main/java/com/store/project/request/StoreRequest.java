package com.store.project.request;

import com.store.project.entity.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreRequest {

    @NotBlank(message = "Should not me empty")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Should not me empty")
    @Size(max = 100)
    private String address;

    @NotBlank(message = "Should not me empty")
    @Size(max = 20)
    private String phone;

    @NotBlank(message = "Should not me empty")
    @Size(max = 50)
    private String workingHours;

}
