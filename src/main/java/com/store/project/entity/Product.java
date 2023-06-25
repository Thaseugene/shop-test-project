package com.store.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private int quantity;

    private String description;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && quantity == product.quantity &&
                Objects.equals(id, product.id) && Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) && Objects.equals(store, product.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity, description, store);
    }
}
