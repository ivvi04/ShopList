package ru.lakeevda.listproductservice.application.port.in;

import ru.lakeevda.listproductservice.application.boundary.model.list.ListRequest;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListResponse;

import java.util.List;

public interface ListUseCase {
    ListResponse getById(Long id);
    List<ListResponse> getAllByPhone(Long phone);
    ListResponse create(ListRequest listRequest);
    void update(Long id, ListRequest listRequest);
    void delete(Long id, Long phone);
    ListResponse addUser(Long id, Long phone);
    ListResponse deleteUser(Long id, Long phone);
}
