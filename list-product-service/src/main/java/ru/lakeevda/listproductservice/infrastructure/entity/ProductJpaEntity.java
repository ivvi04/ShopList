package ru.lakeevda.listproductservice.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", schema = "list_product_service", catalog = "shoplist")
public class ProductJpaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "list_id", nullable = false)
    private Long listId;

    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @Column(name = "price")
    private BigInteger price;

    @Column(name = "url", length = -1)
    private String url;

    @Column(name = "image", length = -1)
    private String image;

    @Column(name = "purchased", nullable = false)
    private Boolean purchased;
}
