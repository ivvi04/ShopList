package ru.lakeevda.listproductservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", schema = "list_product_service", catalog = "shoplist")
public class ProductEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @Column(name = "price", nullable = true, precision = 0)
    private BigInteger price;

    @Column(name = "url", nullable = true, length = -1)
    private String url;

    @Column(name = "list_id", nullable = false)
    private Long listId;

    @Column(name = "image", nullable = true, length = -1)
    private String image;

    @Column(name = "purchased", nullable = false)
    private Boolean purchased;
}
