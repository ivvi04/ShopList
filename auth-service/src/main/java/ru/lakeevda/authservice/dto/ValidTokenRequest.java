package ru.lakeevda.authservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class ValidTokenRequest {
    @Schema(description = "Телефон", example = "89121234567")
    @Size(min = 11, max = 11, message = "Длина телефона должна быть 11 символов")
    @NotBlank(message = "Номер телефона не может быть пустыми")
    private Integer phone;

    @Schema(description = "Токен доступа", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
    private String token;
}
