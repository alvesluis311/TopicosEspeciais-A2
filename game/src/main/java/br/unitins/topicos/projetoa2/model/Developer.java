package br.unitins.topicos.projetoa2.model;

import java.time.LocalDate;

import br.unitins.topicos.projetoa2.dto.DeveloperDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

@Entity
@Table(name = "developers")
@Serdeable
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private LocalDate dataFundacao;

    public Developer() {}

    public Developer(DeveloperDTO developerDTO) {
        this.nome = developerDTO.nome();
        this.dataFundacao = developerDTO.dataFundacao();
    }

    public void setDados(DeveloperDTO developerDTO) {
        this.nome = developerDTO.nome();
        this.dataFundacao = developerDTO.dataFundacao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
}
