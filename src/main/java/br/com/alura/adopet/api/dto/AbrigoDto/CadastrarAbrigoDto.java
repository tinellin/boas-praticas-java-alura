package br.com.alura.adopet.api.dto.AbrigoDto;

import jakarta.validation.constraints.NotBlank;

public record CadastrarAbrigoDto(
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank String email
) {}
