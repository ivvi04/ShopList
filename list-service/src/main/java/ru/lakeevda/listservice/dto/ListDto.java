package ru.lakeevda.listservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lakeevda.listservice.enums.ListStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDto {
    private long id;
    private String name;
    private Integer userPhone;
    @Enumerated(EnumType.STRING)
    private ListStatus status;
}
