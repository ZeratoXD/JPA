package com.salarios.jpa.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_FUNCIONARIO")
public class Funcionario extends Pessoa {

	@Column(nullable = false, unique = true)
	@NotNull(message = "O atributo função é Obrigatório")
	private String funcao;

	@NotNull(message = "O atributo salario é Obrigatório")
	private BigDecimal salario;

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Funcionario{" + "nome='" + getNome() + '\'' + ", dataNascimento=" + getDataDeNascimento() + ", salario="
				+ salario + ", funcao='" + funcao + '\'' + '}';

	}
}