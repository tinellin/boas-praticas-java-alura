package br.com.alura.adopet.api.dto.AdocaoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReprovacaoAdocaoDto(@NotNull  Long idAdocao, @NotBlank String justificativa) {
}
