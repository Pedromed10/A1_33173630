package br.edu.up.daos;

import br.edu.up.models.Aluno;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeAlunos {
    private List<Aluno> listaDeAlunos;

    public GerenciadorDeAlunos() {
        listaDeAlunos = new ArrayList<>();
    }

    public void lerArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split(";");
                if (values.length == 3) {
                    try {

                        String notaStr = values[2].replace(',', '.');
                        Aluno aluno = new Aluno(values[0], values[1], Double.parseDouble(notaStr));
                        listaDeAlunos.add(aluno);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter a nota para número: " + values[2]);
                    }
                } else {
                    System.out.println("Linha inválida encontrada: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processarDados() {
        int totalAlunos = listaDeAlunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        double somaNotas = 0.0;

        for (Aluno aluno : listaDeAlunos) {
            double nota = aluno.getNota();
            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }
            if (nota < menorNota) {
                menorNota = nota;
            }
            if (nota > maiorNota) {
                maiorNota = nota;
            }
            somaNotas += nota;
        }

        double mediaNota = somaNotas / totalAlunos;

        try (PrintWriter pw = new PrintWriter(new File("resumo.csv"))) {
            pw.println("Total de alunos," + totalAlunos);
            pw.println("Aprovados," + aprovados);
            pw.println("Reprovados," + reprovados);
            pw.println("Menor nota," + menorNota);
            pw.println("Maior nota," + maiorNota);
            pw.println("Média geral," + mediaNota);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> getListaDeAlunos() {
        return listaDeAlunos;
    }
}
