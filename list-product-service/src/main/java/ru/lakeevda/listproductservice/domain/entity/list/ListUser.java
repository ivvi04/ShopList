package ru.lakeevda.listproductservice.domain.entity.list;

import lombok.Getter;

@Getter
public class ListUser {
    private ListId listId;
    private final ListUserPhone userPhone;
    private final Boolean isAuthor;

    private ListUser(ListUserPhone userPhone, Boolean isAuthor) {
        this.userPhone = userPhone;
        this.isAuthor = isAuthor;
    }

    private ListUser(ListId listId, ListUserPhone userPhone, Boolean isAuthor) {
        this.listId = listId;
        this.userPhone = userPhone;
        this.isAuthor = isAuthor;
    }

    public static ListUser create(ListUserPhone userPhone) {
        return new ListUser(userPhone, Boolean.TRUE);
    }

    public static ListUser create(ListUserPhone userPhone, Boolean isAuthor) {
        return new ListUser(userPhone, isAuthor);
    }

    public static ListUser restore(ListId listId, ListUserPhone userPhone, Boolean isAuthor) {
        return new ListUser(listId, userPhone, isAuthor);
    }
}
