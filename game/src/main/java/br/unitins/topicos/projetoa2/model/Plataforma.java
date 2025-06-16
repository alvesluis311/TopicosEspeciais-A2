package br.unitins.topicos.projetoa2.model;

import java.time.LocalDate;

import br.unitins.topicos.projetoa2.dto.PlataformaDTO;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plataformas")
@Serdeable
public class Plataforma {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private LocalDate dataLancamento;

    public Plataforma () {}

    public Plataforma(PlataformaDTO plataformaDTO) {
        this.nome = plataformaDTO.nome();
        this.dataLancamento = plataformaDTO.dataLancamento();
    }

    public void setDados(PlataformaDTO plataformaDTO) {
        this.nome = plataformaDTO.nome();
        this.dataLancamento = plataformaDTO.dataLancamento();
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
