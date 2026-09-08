package ru.lakeevda.listproductservice.adapters.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListRequest;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListResponse;
import ru.lakeevda.listproductservice.application.port.in.ListUseCase;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListController {
    private final ListUseCase listUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<ListResponse> getById(@PathVariable Long id) {
        ListResponse list = listUseCase.getById(id);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<ListResponse> create(@RequestBody ListRequest list) {
        ListResponse resultList = listUseCase.create(list);
        return ResponseEntity.ok().body(resultList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody ListRequest list) {
        listUseCase.update(id, list);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @RequestParam Long phone) {
        listUseCase.delete(id, phone);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<ListResponse>> getAllByPhone(@PathVariable Long phone) {
        List<ListResponse> lists = listUseCase.getAllByPhone(phone);
        return ResponseEntity.ok().body(lists);
    }

    @PutMapping("/{id}/user/add")
    public ResponseEntity<ListResponse> addUser(@PathVariable Long id,
                                                @RequestParam Long phone) {
        ListResponse list = listUseCase.addUser(id, phone);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}/user/delete")
    public ResponseEntity<ListResponse> deleteUser(@PathVariable Long id,
                                                   @RequestParam Long phone) {
        ListResponse list = listUseCase.deleteUser(id, phone);
        return ResponseEntity.ok().body(list);
    }
}
