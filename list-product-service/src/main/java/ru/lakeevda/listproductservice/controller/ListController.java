package ru.lakeevda.listproductservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lakeevda.listproductservice.dto.ShopListDto;
import ru.lakeevda.listproductservice.service.ListService;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListController {
    private final ListService listService;

    @GetMapping("/{id}")
    public ResponseEntity<ShopListDto> getList(@PathVariable Long id) {
        ShopListDto list = listService.getListById(id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<ShopListDto>> getLists(@PathVariable long phone) {
        List<ShopListDto> lists = listService.getListByUserPhone(phone);
        return ResponseEntity.ok().body(lists);
    }

    @PostMapping
    public ResponseEntity<ShopListDto> addList(@RequestBody ShopListDto list) {
        ShopListDto resultList = listService.addList(list);
        return ResponseEntity.ok().body(resultList);
    }

    @PutMapping("/phone/{phone}/update")
    public ResponseEntity<Void> updateList(@PathVariable long phone,
                                           @RequestBody ShopListDto list) {
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
    public ResponseEntity<ShopListDto> addUserToList(@PathVariable Long id,
                                                     @RequestParam long phone) {
        ShopListDto list = listService.addUserToList(id, phone);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}/user/delete")
    public ResponseEntity<ShopListDto> deleteUserFromList(@PathVariable Long id,
                                                          @RequestParam long phone) {
        ShopListDto list = listService.deleteUserFromList(id, phone);
        return ResponseEntity.ok().body(list);
    }
}
