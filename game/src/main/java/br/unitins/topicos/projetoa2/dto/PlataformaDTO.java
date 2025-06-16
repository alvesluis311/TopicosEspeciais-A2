package br.unitins.topicos.projetoa2.dto;

import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record PlataformaDTO(
    @NotBlank(message = "O nome da plataforma é obrigatório")
    String nome,
    LocalDate dataLancamento
) {
    
}
