package ru.lakeevda.listservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.listservice.dto.ListDto;
import ru.lakeevda.listservice.service.ListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListController {
    private final ListService listService;

    @GetMapping("/{id}")
    public ResponseEntity<ListDto> getList(@PathVariable Long id) {
        ListDto list = listService.getListById(id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<ListDto>> getLists(@PathVariable long phone) {
        List<ListDto> lists = listService.getListByUserPhone(phone);
        return ResponseEntity.ok().body(lists);
    }

    @PostMapping
    public ResponseEntity<ListDto> addList(@RequestBody ListDto list) {
        ListDto resultList = listService.addList(list);
        return ResponseEntity.ok().body(resultList);
    }

    @PutMapping("/phone/{phone}/update")
    public ResponseEntity<Void> updateList(@PathVariable long phone,
                                           @RequestBody ListDto list) {
        listService.updateList(list, phone);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/phone/{phone}/delete/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable long phone,
                                           @PathVariable Long id) {
        listService.deleteList(id, phone);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/user/add")
    public ResponseEntity<ListDto> addUserToList(@PathVariable Long id,
                                                 @RequestParam long phone) {
        ListDto list = listService.addUserToList(id, phone);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}/user/delete")
    public ResponseEntity<ListDto> deleteUserFromList(@PathVariable Long id,
                                                      @RequestParam long phone) {
        ListDto list = listService.deleteUserFromList(id, phone);
        return ResponseEntity.ok().body(list);
    }
}
