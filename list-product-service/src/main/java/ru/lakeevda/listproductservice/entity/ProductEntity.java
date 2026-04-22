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
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private BigInteger price;
    @Basic
    @Column(name = "url", nullable = true, length = -1)
    private String url;
    @Basic
    @Column(name = "shop_list_id", nullable = false)
    private long shopListId;
    @Basic
    @Column(name = "image", nullable = true, length = -1)
    private String image;
    @Basic
    @Column(name = "purchased", nullable = false)
    private boolean purchased;
}
