package ru.lakeevda.listproductservice.application.usecase;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListRequest;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListResponse;
import ru.lakeevda.listproductservice.application.mapper.ListMapper;
import ru.lakeevda.listproductservice.application.port.in.ListUseCase;
import ru.lakeevda.listproductservice.domain.entity.list.List;
import ru.lakeevda.listproductservice.domain.entity.list.ListId;
import ru.lakeevda.listproductservice.domain.entity.list.ListName;
import ru.lakeevda.listproductservice.domain.entity.list.ListStatus;
import ru.lakeevda.listproductservice.domain.entity.list.ListUserPhone;
import ru.lakeevda.listproductservice.domain.exception.ListExistException;
import ru.lakeevda.listproductservice.domain.exception.ListNotFoundException;
import ru.lakeevda.listproductservice.domain.exception.UserNotAuthorException;
import ru.lakeevda.listproductservice.domain.repository.ListRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListUseCaseImpl implements ListUseCase {
    private final ListRepository repository;

    @Override
    public ListResponse getById(Long id) {
        return ListMapper.toDto(getList(id));
    }

    @Override
    public java.util.List<ListResponse> getAllByPhone(Long phone) {
        java.util.List<List> lists = repository.findAllByUserPhone(ListUserPhone.of(phone));
        return lists.stream()
                .map(ListMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ListResponse create(ListRequest listRequest) {
        ListName name = ListName.of(listRequest.name());
        ListUserPhone userPhone = ListUserPhone.of(listRequest.phone());
        if (repository.findByNameAndUserPhone(name, userPhone).isPresent())
            throw new ListExistException();
        List list = ListMapper.toDomain(listRequest, ListStatus.CREATED);
        return ListMapper.toDto(repository.save(list));
    }

    @Override
    public void update(Long id, ListRequest listRequest) {
        List list = getList(id);

        if (list.isUserNotAuthor(ListUserPhone.of(listRequest.phone()))) throw new UserNotAuthorException();

        list.updateName(ListName.of(listRequest.name()));
        repository.save(list);
    }

    @Override
    public void delete(Long id, Long phone) {
        List list = getList(id);

        if (list.isUserNotAuthor(ListUserPhone.of(phone))) throw new UserNotAuthorException();

        repository.delete(list);
    }

    @Override
    public ListResponse addUser(Long id, Long phone) {
        List list = getList(id);

        ListUserPhone listUserPhone = ListUserPhone.of(phone);

        if (!list.hasUser(listUserPhone)) {
            list.addListUser(listUserPhone, false);
            repository.save(list);
        }

        return ListMapper.toDto(list);
    }

    @Override
    public ListResponse deleteUser(Long id, Long phone) {
        List list = getList(id);

        list.removeListUser(ListUserPhone.of(phone));
        return ListMapper.toDto(list);
    }

    private @NonNull List getList(Long id) {
        return repository.findById(ListId.of(id)).orElseThrow(ListNotFoundException::new);
    }
}
