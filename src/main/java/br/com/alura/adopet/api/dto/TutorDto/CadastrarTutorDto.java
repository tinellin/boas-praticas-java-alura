package br.com.alura.adopet.api.dto.TutorDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastrarTutorDto(
        @NotBlank
        String nome,
        @NotBlank
        String email,

        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String telefone
) {
}
