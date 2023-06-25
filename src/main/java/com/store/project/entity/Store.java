package com.store.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String workingHours;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Product> products;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) && Objects.equals(name, store.name) &&
                Objects.equals(address, store.address) && Objects.equals(phone, store.phone) &&
                Objects.equals(workingHours, store.workingHours) && Objects.equals(products, store.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, workingHours, products);
    }
}
