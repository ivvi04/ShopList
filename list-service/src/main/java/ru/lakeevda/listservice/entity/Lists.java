package ru.lakeevda.listservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import ru.lakeevda.listservice.enums.ListStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lists", schema = "public", catalog = "shoplist")
public class Lists {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "author_id", nullable = false)
    private long authorId;

    @Column(name = "status", nullable = false, columnDefinition = "status_enum")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ListStatus status;

    @ManyToMany
    @JoinTable(name = "user_list",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "product",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"))
    private List<Product> products = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }
    public void addProduct (Product product) {
        products.add(product);
    }

    public void deleteProduct (Product product) {
        products.remove(product);
    }
}
