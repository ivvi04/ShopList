package ru.lakeevda.listproductservice.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "list_users", schema = "list_product_service", catalog = "shoplist")
@IdClass(ListUserJpaId.class)
public class ListUserJpaEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListJpaEntity listJpaEntity;

    @Id
    @Column(name = "user_phone", nullable = false)
    private Long userPhone;

    @Column(name = "is_author", nullable = false)
    private Boolean isAuthor;
}
