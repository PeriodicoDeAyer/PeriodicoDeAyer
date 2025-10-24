package com.femcoders.periodico_ayer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArticleRequest {
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no superar los 255 caracteres.")
    private String title;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(min = 50, max = 2000, message = "El contenido debe tener entre 50 y 2000 caracteres")  
    private String content;

    @NotBlank(message = "El contenido es obligatorio")
    private String category;

    @NotNull(message = "El ID es obligatorio")
    private Long userId;

}
