package br.unitins.topicos.projetoa2.dto;

import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record GameDTO(
    String nome,
    String genero,
    Double preco,
    String plataforma,
    Long idDeveloper,
    LocalDate dataLancamento
) {}

