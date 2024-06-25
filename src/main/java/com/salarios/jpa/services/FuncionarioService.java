package com.salarios.jpa.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.salarios.jpa.models.Funcionario;

public class FuncionarioService {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    public List<Funcionario> inicializarFuncionarios() {
        return new ArrayList<>(Arrays.asList(
                new Funcionario("João", LocalDate.of(1990, 5, 15), new BigDecimal("2500.00"), "Assistente"),
                new Funcionario("Maria", LocalDate.of(1985, 8, 20), new BigDecimal("3000.00"), "Gerente"),
                new Funcionario("Ana", LocalDate.of(1995, 10, 30), new BigDecimal("4000.00"), "Diretor"),
                new Funcionario("Carlos", LocalDate.of(1970, 12, 10), new BigDecimal("4500.00"), "Gerente")

    public void removerFuncionario(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equals(nome));
    }

    public void imprimirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        System.out.println("Lista de Funcionários:");
        for (Funcionario f : funcionarios) {
            System.out.println(formatarFuncionario(f, dateFormatter, decimalFormat));
        }
    }

    private String formatarFuncionario(Funcionario f, DateTimeFormatter dateFormatter, DecimalFormat decimalFormat) {
        return "Nome: " + f.getNome() +
                ", Data de Nascimento: " + f.getDataDeNascimento().format(dateFormatter) +
                ", Salário: " + decimalFormat.format(f.getSalario()) +
                ", Função: " + f.getFuncao();
    }

    public void aumentarSalario(List<Funcionario> funcionarios, BigDecimal percentual) {
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1).add(percentual))));
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        System.out.println("\nFuncionários agrupados por função:");
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Função: " + funcao);
            for (Funcionario f : funcionariosPorFuncao.get(funcao)) {
                System.out.println("\t" + f);
            }
        }
    }

    public void imprimirAniversariantes(List<Funcionario> funcionarios, int... meses) {
        Set<Integer> mesesSet = Arrays.stream(meses).boxed().collect(Collectors.toSet());
        System.out.println("\nFuncionários que fazem aniversário nos meses especificados:");
        funcionarios.stream()
                .filter(f -> mesesSet.contains(f.getDataDeNascimento().getMonthValue()))
                .forEach(System.out::println);
    }

    public Funcionario encontrarFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        return Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataDeNascimento));
    }

    public void imprimirFuncionarioMaisVelho(Funcionario funcionarioMaisVelho) {
        int idade = calcularIdade(funcionarioMaisVelho.getDataDeNascimento(), LocalDate.now());
        System.out.println("\nFuncionário com maior idade: Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + idade);
    }

    private int calcularIdade(LocalDate nascimento, LocalDate hoje) {
        return hoje.getYear() - nascimento.getYear();
    }

    public void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        System.out.println("\nLista de Funcionários por ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
    }

    public void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        System.out.println("\nTotal dos salários dos funcionários: " + decimalFormat.format(totalSalarios));
    }

    public void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        System.out.println("\nSalários mínimos por funcionário:");
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + decimalFormat.format(salariosMinimos) + " salários mínimos.");
        }
    }
}

