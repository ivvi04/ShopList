package ru.lakeevda.listproductservice.domain.entity.list;

import lombok.Getter;
import ru.lakeevda.listproductservice.domain.entity.product.Product;

import java.util.ArrayList;

@Getter
public class List {
    private ListId id;
    private ListName name;
    private ListStatus status;
    private final java.util.List<ListUser> listUsers;
    private final java.util.List<Product> products;

    private List(ListName name, ListStatus status, java.util.List<ListUser> listUsers) {
        this.name = name;
        this.status = status;
        this.listUsers = listUsers;
        this.products = new ArrayList<>();
    }

    private List(ListId id, ListName name, ListStatus status, java.util.List<ListUser> listUsers, java.util.List<Product> products) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.listUsers = listUsers;
        this.products = products;
    }

    public static List create(ListName name,
                              ListStatus status,
                              java.util.List<ListUser> listUsers) {
        return new List(name, status, listUsers);
    }

    public static List restore(ListId id,
                               ListName name,
                               ListStatus status,
                               java.util.List<ListUser> listUsers,
                               java.util.List<Product> products) {
        return new List(id, name, status, listUsers, products);
    }

    public void updateName(ListName newName) {
        this.name = newName;
    }

    public void updateStatus(ListStatus newStatus) {
        this.status = newStatus;
    }

    public void addListUser(ListUserPhone userPhone, Boolean isAuthor) {
        this.listUsers.add(ListUser.restore(this.id, userPhone, isAuthor));
    }

    public void removeListUser(ListUserPhone userPhone) {
        this.listUsers.removeIf(user -> user.getUserPhone().getValue().equals(userPhone.getValue()));
    }

    public boolean isUserNotAuthor(ListUserPhone userPhone) {
        return this.listUsers.stream()
                .noneMatch(user -> user.getUserPhone().getValue().equals(userPhone.getValue()) && user.getIsAuthor());
    }

    public boolean hasUser(ListUserPhone userPhone) {
        return this.listUsers.stream()
                .anyMatch(user -> user.getUserPhone().getValue().equals(userPhone.getValue()));
    }
}
