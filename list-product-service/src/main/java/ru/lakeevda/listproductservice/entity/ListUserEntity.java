package ru.lakeevda.listproductservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "list_users", schema = "list_product_service", catalog = "shoplist")
public class ListUserEntity {

    @Id
    @Column(name = "list_id", nullable = false)
    private Long listId;

    @Id
    @Column(name = "user_phone", nullable = false)
    private Long userPhone;

    @Column(name = "is_author", nullable = false)
    private Boolean isAuthor;
}
