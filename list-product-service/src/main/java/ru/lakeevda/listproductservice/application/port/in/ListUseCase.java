package ru.lakeevda.listproductservice.application.port.in;

import ru.lakeevda.listproductservice.application.boundary.model.list.ListDto;

import java.util.List;

public interface ListUseCase {
    ListDto getById(Long id);
    List<ListDto> getAllByPhone(Long phone);
    ListDto create(ListDto list);
    void update(ListDto list, Long phone);
    void delete(Long id, Long phone);
    ListDto addUser(Long id, Long phone);
    ListDto deleteUser(Long id, Long phone);
}
