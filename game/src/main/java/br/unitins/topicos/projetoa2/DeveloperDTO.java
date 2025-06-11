package br.unitins.topicos.projetoa2;

import java.time.LocalDate;

public record DeveloperDTO(
    String nome,
    LocalDate dataFundacao
) {}
