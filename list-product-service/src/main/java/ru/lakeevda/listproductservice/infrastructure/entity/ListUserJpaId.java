package ru.lakeevda.listproductservice.infrastructure.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ListUserJpaId implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "list_id")
    private ListJpaEntity listJpaEntity;

    @Id
    private Long userPhone;
}
