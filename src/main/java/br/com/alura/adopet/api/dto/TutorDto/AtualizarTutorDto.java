package br.com.alura.adopet.api.dto.TutorDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarTutorDto (
        @NotNull
        Long id,

        String nome,

        String email,

        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String telefone
) {}
