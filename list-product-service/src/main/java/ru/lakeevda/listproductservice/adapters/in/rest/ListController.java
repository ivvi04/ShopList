package ru.lakeevda.listproductservice.adapters.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.listproductservice.application.boundary.model.list.ListDto;
import ru.lakeevda.listproductservice.application.port.in.ListUseCase;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListController {
    private final ListUseCase listUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<ListDto> getList(@PathVariable Long id) {
        ListDto list = listUseCase.getById(id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<ListDto>> getLists(@PathVariable long phone) {
        List<ListDto> lists = listUseCase.getListsByUserPhone(phone);
        return ResponseEntity.ok().body(lists);
    }

    @PostMapping
    public ResponseEntity<ListDto> createList(@RequestBody ListDto list) {
        ListDto resultList = listUseCase.create(list);
        return ResponseEntity.ok().body(resultList);
    }

    @PutMapping("/phone/{phone}/update")
    public ResponseEntity<Void> updateList(@PathVariable long phone,
                                           @RequestBody ListDto list) {
        listUseCase.update(list, phone);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/phone/{phone}/delete/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable long phone,
                                           @PathVariable Long id) {
        listUseCase.delete(id, phone);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/user/add")
    public ResponseEntity<ListDto> addUser(@PathVariable Long id,
                                           @RequestParam long phone) {
        ListDto list = listUseCase.addUser(id, phone);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}/user/delete")
    public ResponseEntity<ListDto> deleteUser(@PathVariable Long id,
                                              @RequestParam long phone) {
        ListDto list = listUseCase.deleteUser(id, phone);
        return ResponseEntity.ok().body(list);
    }
}
