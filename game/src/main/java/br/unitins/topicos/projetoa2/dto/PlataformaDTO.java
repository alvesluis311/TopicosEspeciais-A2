package br.unitins.topicos.projetoa2.dto;

import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record PlataformaDTO(
    String nome,
    LocalDate dataLancamento
) {
    
}
