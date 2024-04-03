package ru.lakeevda.listservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lakeevda.listservice.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDto {
    private long id;
    private String name;
    private Integer phone;
    private List<User> users;
}
