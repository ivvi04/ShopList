package ru.lakeevda.listproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lakeevda.listproductservice.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopListDto {
    private Long id;
    private String name;
    private Long phone;
    private List<Long> userIds;
}
