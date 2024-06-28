package br.edu.up;

import br.edu.up.daos.GerenciadorDeAlunos;

public class Program {
    public static void main(String[] args) {
        GerenciadorDeAlunos gerenciador = new GerenciadorDeAlunos();

        System.out.println("Iniciando a leitura do arquivo alunos.csv");
        gerenciador.lerArquivo("alunos.csv");
        System.out.println("Leitura do arquivo concluída");

        System.out.println("Iniciando o processamento dos dados");
        gerenciador.processarDados();
        System.out.println("Processamento dos dados concluído");
    }
}
