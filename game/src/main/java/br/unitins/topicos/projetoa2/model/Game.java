package br.unitins.topicos.projetoa2.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import br.unitins.topicos.projetoa2.dto.GameDTO;
import io.micronaut.serde.annotation.Serdeable;

@Entity
@Table(name = "games")
@Serdeable
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String genero;

    @Column(nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "developer", nullable = false)
    private Developer developer;
    
    @ManyToOne
    @JoinColumn(name = "plataforma", nullable = false)
    private Plataforma plataforma;

    @Column(nullable = false)
    private LocalDate dataLancamento;

    public Game() {}

    public Game(GameDTO gameDTO, Developer developer, Plataforma plataforma) {
        this.nome = gameDTO.nome();
        this.genero = gameDTO.genero();
        this.preco = gameDTO.preco();
        this.plataforma = plataforma;
        this.dataLancamento = gameDTO.dataLancamento();
        this.developer = developer;
    }

    public void setDados(GameDTO gameDTO, Developer developer, Plataforma plataforma) {
        this.nome = gameDTO.nome();
        this.genero = gameDTO.genero();
        this.preco = gameDTO.preco();
        this.plataforma = plataforma;
        this.dataLancamento = gameDTO.dataLancamento();
        this.developer = developer;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
}
