package com.femcoders.periodico_ayer.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Nombre no puede estar vacío")
    private String username;

    @Email(message = "Email no puede estar vacío")
    @Email(message = "Email debe ser válido")
    private String email;
}
