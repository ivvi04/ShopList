package ru.lakeevda.listservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.listservice.dto.ListDto;
import ru.lakeevda.listservice.entity.Lists;
import ru.lakeevda.listservice.service.ListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListController {
    private final ListService listService;

    @GetMapping("/{id}")
    public ResponseEntity<Lists> getList(@PathVariable Long id) {
        Lists lists = listService.findListById(id);
        return ResponseEntity.ok().body(lists);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Lists>> getLists(@RequestParam Integer userPhone) {
        List<Lists> lists = listService.getListByUserPhone(userPhone);
        return ResponseEntity.ok().body(lists);
    }

    @PostMapping
    public ResponseEntity<Lists> addList(@RequestBody Lists list) {
        Lists resultList = listService.addList(list);
        return ResponseEntity.ok().body(resultList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lists> addUserToList(@PathVariable Long id,
                                               @RequestParam Integer userPhone) {
        Lists list = listService.addUserToList(id, userPhone);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Lists> deleteUserFromList(@PathVariable Long id,
                                                    @RequestParam Integer userPhone) {
        Lists list = listService.deleteUserFromList(id, userPhone);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id,
                                           @RequestParam Integer userPhone) {
        listService.deleteList(id, userPhone);
        return ResponseEntity.ok().build();
    }
}
