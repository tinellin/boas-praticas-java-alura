package br.com.alura.adopet.api.dto.PetDto;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarPetDto(
        @NotNull TipoPet tipo,
        @NotBlank String nome,
        @NotBlank String raca,
        @NotNull Integer idade,
        @NotBlank String cor,
        @NotNull float peso,
        @NotNull String nomeAbrigo
    )
{}
