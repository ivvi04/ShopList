package ru.lakeevda.listproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDto {
    private Long id;
    private String name;
    private HashMap<Long, Boolean> userPhones;
}
