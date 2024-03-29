package ru.lakeevda.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product", schema = "public", catalog = "shoplist")
public class Product {
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
    @Column(name = "list_id", nullable = false)
    private long listId;
    @Basic
    @Column(name = "image", nullable = true)
    private byte[] image;
    @Basic
    @Column(name = "purchased", nullable = false)
    private boolean purchased;
}
