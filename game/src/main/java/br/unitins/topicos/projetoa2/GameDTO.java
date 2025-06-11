package br.unitins.topicos.projetoa2;

import io.micronaut.core.annotation.Introspected;
import java.time.LocalDate;

@Introspected
public record GameDTO(
    String nome,
    String genero,
    Double preco,
    String plataforma,
    Long idDeveloper,
    LocalDate dataLancamento
) {}

