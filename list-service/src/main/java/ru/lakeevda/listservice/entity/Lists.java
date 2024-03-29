package ru.lakeevda.listservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
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
    @Column(name = "name", nullable = false, length = 100)
    private String name;
//    @Basic
//    @Column(name = "author_id", nullable = false)
//    private long authorId;

    @OneToOne
    @JoinTable(name = "user",
    joinColumns = @JoinColumn(name = "id", referencedColumnName = "author_id"))
    private User userAuthor;
//
//    @Formula("(select user.phone from user, lists where lists.id = id and user.id = lists.author_id)")
//    private Integer userPhone;

    @Column(name = "status", nullable = false, columnDefinition = "status_enum")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ListStatus status;

    @ManyToMany
    @JoinTable(name = "user_list",
            joinColumns = @JoinColumn(name = "list_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users = new ArrayList<>();

    public void addUserToList (User user) {
        users.add(user);
    }

    public void deleteUserFromList (User user) {
        users.remove(user);
    }
}
