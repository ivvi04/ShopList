package ru.lakeevda.listproductservice.application.port.in;

import ru.lakeevda.listproductservice.application.boundary.model.list.ListDto;

import java.util.List;

public interface ListUseCase {
    ListDto getById(Long id);
    List<ListDto> getListsByUserPhone(Long userPhone);
    ListDto create(ListDto list);
    void update(ListDto list, Long userPhone);
    void delete(Long id, Long userPhone);
    ListDto addUser(Long id, Long userPhone);
    ListDto deleteUser(Long id, Long userPhone);
}
