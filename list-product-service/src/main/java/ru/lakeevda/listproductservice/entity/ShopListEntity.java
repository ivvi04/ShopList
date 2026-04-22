package ru.lakeevda.listproductservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import ru.lakeevda.listproductservice.enums.ShopListStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop_lists", schema = "list_product_service", catalog = "shoplist")
public class ShopListEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(name = "shop_list_users",
            joinColumns = @JoinColumn(name = "shop_list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "product",
            joinColumns = @JoinColumn(name = "shop_list_id", referencedColumnName = "id"))
    private List<ProductEntity> products = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }
    public void addProduct (ProductEntity product) {
        products.add(product);
    }

    public void deleteProduct (ProductEntity product) {
        products.remove(product);
    }
}
