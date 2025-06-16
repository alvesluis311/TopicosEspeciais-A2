package br.unitins.topicos.projetoa2.dto;

import java.time.LocalDate;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Serdeable
public record GameDTO(
    @NotBlank(message = "O nome do jogo é obrigatório")
    String nome,
    String genero,

    @NotNull(message = "O preço do jogo é obrigatório")
    @PositiveOrZero(message = "O preço não pode ser menor que 0")
    Double preco,

    @NotNull(message = "A plataforma do jogo é obrigatório")
    @Min(1)
    Long idPlataforma,

    @NotNull(message = "A developer do jogo é obrigatório")
    @Min(1)
    Long idDeveloper,

    @NotNull(message = "A data de lançamento do jogo é obrigatório")
    LocalDate dataLancamento
) {}

