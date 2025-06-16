package br.unitins.topicos.projetoa2.dto;

import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record DeveloperDTO(
    @NotBlank(message = "O nome da developer é obrigatório")
    String nome,
    LocalDate dataFundacao
) {}
