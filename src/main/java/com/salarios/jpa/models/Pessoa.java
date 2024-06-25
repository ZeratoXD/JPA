package com.salarios.jpa.models;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataDeNascimento() {
		return DataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		DataDeNascimento = dataDeNascimento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false, unique = true)
	private String nome;

	@NotNull(message = "O atributo Data é Obrigatório")
	private LocalDate DataDeNascimento;

	@Override
	public String toString() {
		return "Pessoa{" + "nome='" + nome + '\'' + ", DataDeNascimento=" + DataDeNascimento + '}';

	}
}
