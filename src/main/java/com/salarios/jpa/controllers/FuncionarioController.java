package com.salarios.jpa.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.salarios.jpa.models.Funcionario;
import com.salarios.jpa.services.FuncionarioService;

public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController() {
        this.funcionarioService = new FuncionarioService();
    }

    public void executar() {
        List<Funcionario> funcionarios = funcionarioService.inicializarFuncionarios();

        funcionarioService.removerFuncionario(funcionarios, "João");

        funcionarioService.imprimirFuncionarios(funcionarios);

        funcionarioService.aumentarSalario(funcionarios, BigDecimal.valueOf(0.1));

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarioService.agruparPorFuncao(funcionarios);
        funcionarioService.imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        funcionarioService.imprimirAniversariantes(funcionarios, 10, 12);

        Funcionario funcionarioMaisVelho = funcionarioService.encontrarFuncionarioMaisVelho(funcionarios);
        funcionarioService.imprimirFuncionarioMaisVelho(funcionarioMaisVelho);

        funcionarioService.imprimirFuncionariosOrdemAlfabetica(funcionarios);

        funcionarioService.imprimirTotalSalarios(funcionarios);

        funcionarioService.imprimirSalariosMinimos(funcionarios);
    }
}

