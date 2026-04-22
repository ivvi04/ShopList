package ru.lakeevda.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "Имя пользователя", example = "User name")
    private String username;

    @Schema(description = "Телефон пользователя", example = "89121234567")
    private Long phone;

    @Schema(description = "Адрес электронной почты", example = "user@mail.com")
    private String email;
}
