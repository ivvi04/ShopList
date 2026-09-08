package ru.lakeevda.listproductservice.infrastructure.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lists", schema = "list_product_service", catalog = "shoplist")
public class ListJpaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "listJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListUserJpaEntity> listUsers = new ArrayList<>();

    @OneToMany(mappedBy = "listId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductJpaEntity> products = new ArrayList<>();
}
