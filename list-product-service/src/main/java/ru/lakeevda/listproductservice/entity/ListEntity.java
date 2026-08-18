package ru.lakeevda.listproductservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lists", schema = "list_product_service", catalog = "shoplist")
public class ListEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(name = "list_users",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_phone", referencedColumnName = "id"))
    private List<ListUserEntity> listUsers = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "products",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"))
    private List<ProductEntity> products = new ArrayList<>();
}
