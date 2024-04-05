package ru.lakeevda.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на смену пароля")
public class ChangePasswordRequest {

    @Schema(description = "Телефон", example = "89121234567")
    @Size(min = 11, max = 11, message = "Длина телефона должна быть 11 символов")
    @NotBlank(message = "Номер телефона не может быть пустыми")
    private long phone;

    @Schema(description = "Старый пароль", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String oldPassword;

    @Schema(description = "Новый пароль", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;

    @Schema(description = "Подтвержденный новый пароль", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String confirmPassword;
}
