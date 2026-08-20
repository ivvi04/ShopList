package ru.lakeevda.listproductservice.application.usecase;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListDto;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListUserDto;
import ru.lakeevda.listproductservice.application.mapper.ListMapper;
import ru.lakeevda.listproductservice.application.port.in.ListUseCase;
import ru.lakeevda.listproductservice.domain.entity.list.List;
import ru.lakeevda.listproductservice.domain.entity.list.ListId;
import ru.lakeevda.listproductservice.domain.entity.list.ListName;
import ru.lakeevda.listproductservice.domain.entity.list.ListStatus;
import ru.lakeevda.listproductservice.domain.entity.list.ListUserPhone;
import ru.lakeevda.listproductservice.domain.exception.ListExistException;
import ru.lakeevda.listproductservice.domain.exception.ListNotFoundException;
import ru.lakeevda.listproductservice.domain.exception.UserAuthorIsEmptyException;
import ru.lakeevda.listproductservice.domain.exception.UserNotAuthorException;
import ru.lakeevda.listproductservice.domain.repository.ListRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListUseCaseImpl implements ListUseCase {
    private final ListRepository repository;

    @Override
    public ListDto getById(Long id) {
        return ListMapper.toDto(getList(id));
    }

    @Override
    public java.util.List<ListDto> getListsByUserPhone(Long userPhone) {
        java.util.List<List> lists = repository.findAllByUserPhone(ListUserPhone.of(userPhone));
        return lists.stream()
                .map(ListMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ListDto create(ListDto listDto) {
        ListName name = ListName.of(listDto.name());
        ListUserPhone userPhone = ListUserPhone.of(listDto.userPhones().stream()
                .filter(ListUserDto::isAuthor)
                .map(ListUserDto::userPhone)
                .findFirst()
                .orElseThrow(() -> new UserAuthorIsEmptyException("Не передан автор списка")));
        if (repository.findByNameAndUserPhone(name, userPhone).isPresent())
            throw new ListExistException("Список с таким названием уже существует");
        List list = ListMapper.toDomain(listDto, ListStatus.CREATED);
        return ListMapper.toDto(repository.save(list));
    }

    @Override
    public void update(ListDto listDto, Long userPhone) {
        List list = getList(listDto.id());

        if (!list.isUserAuthor(ListUserPhone.of(userPhone))) {
            throw new UserNotAuthorException("Только у автора есть права на редактирование!");
        }

        list.updateName(ListName.of(listDto.name()));
        repository.save(list);
    }

    @Override
    public void delete(Long id, Long userPhone) {
        List listEntity = getList(id);

        if (!listEntity.isUserAuthor(ListUserPhone.of(userPhone))) {
            throw new UserNotAuthorException("Только у автора есть права на удаление!");
        }

        repository.delete(listEntity);
    }

    @Override
    public ListDto addUser(Long id, Long userPhone) {
        List list = getList(id);

        ListUserPhone listUserPhone = ListUserPhone.of(userPhone);

        if (!list.hasUser(listUserPhone)) {
            list.addListUser(listUserPhone, false);
            repository.save(list);
        }

        return ListMapper.toDto(list);
    }

    @Override
    public ListDto deleteUser(Long id, Long userPhone) {
        List list = getList(id);

        list.removeListUser(ListUserPhone.of(userPhone));
        return ListMapper.toDto(list);
    }

    private @NonNull List getList(Long id) {
        return repository.findById(ListId.of(id))
                .orElseThrow(() -> new ListNotFoundException("Список не найден!"));
    }
}
