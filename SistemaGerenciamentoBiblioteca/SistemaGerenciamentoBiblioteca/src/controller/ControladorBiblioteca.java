import java.util.List;
import java.util.Scanner;
import java.util.Date;

import model.Livro;
import model.Usuario;
import model.Emprestimo;
import model.OperacaoInvalidaException;

import view.VisaoBiblioteca;
import view.PreCarregadorDados;

public class ControladorBiblioteca {
    
    private final List<Livro> livros;
    private final List<Usuario> usuarios;
    private final List<Emprestimo> emprestimos;
    private final VisaoBiblioteca visao;

    public ControladorBiblioteca() {
        PreCarregadorDados.carregarDadosIniciais();
        this.livros = PreCarregadorDados.getLivros();
        this.usuarios = PreCarregadorDados.getUsuarios();
        this.emprestimos = PreCarregadorDados.getEmprestimos();
        this.visao = new VisaoBiblioteca(livros, usuarios, emprestimos);
    }

    public void executar() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;  // Inicializa a variável 'opcao'
    
        do {
            visao.exibirMenu();
            int opcaoEscolhida = obterOpcaoMenu(scanner);
    
            try {
                switch (opcaoEscolhida) {
                    case 1:
                        listarLivros();
                        break;
                    case 2:
                        listarUsuarios();
                        break;
                    case 3:
                        listarEmprestimos();
                        break;
                    case 4:
                        gerarRelatorioAtrasos();
                        break;
                    case 5:
                        gerarRelatorioEmprestimosAtivos();
                        break;
                    case 6:
                        realizarEmprestimo(scanner);
                        break;
                    case 7:
                        realizarDevolucao(scanner);
                        break;
                    case 8:
                        System.out.println("Encerrando o sistema. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }
            } catch (OperacaoInvalidaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
    
            opcao = opcaoEscolhida;  // Atualiza a variável 'opcao' com a opção escolhida
    
        } while (opcao != 8);  // Condição do loop: enquanto a opção não for 8 (encerrar)
    
        scanner.close();
    }

    private int obterOpcaoMenu(Scanner scanner) {
        int opcao = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();  // Limpa o buffer do scanner
                if (opcao >= 1 && opcao <= 8) {
                    entradaValida = true;
                } else {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine();  // Limpa a entrada inválida
            }
        }
        return opcao;
    }

    private int obterIDValido(Scanner scanner, int tamanhoLista) {
        int id = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print("Informe o ID: ");
                id = scanner.nextInt();
                scanner.nextLine();  // Limpa o buffer do scanner
                if (id >= 0 && id < tamanhoLista) {
                    entradaValida = true;
                } else {
                    System.out.println("ID inválido! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
                scanner.nextLine();  // Limpa a entrada inválida
            }
        }
        return id;
    }

    private void listarLivros() {
        visao.listarLivros();
    }

    private void listarUsuarios() {
        visao.listarUsuarios();
    }

    private void listarEmprestimos() {
        visao.listarEmprestimos();
    }

    private void gerarRelatorioAtrasos() {
        System.out.println("\n=== Relatório de Atrasos ===");

        emprestimos.sort((e1, e2) -> e2.calcularAtraso() - e1.calcularAtraso());

        for (Emprestimo emprestimo : emprestimos) {
            int atraso = emprestimo.calcularAtraso();
            if (atraso > 0) {
                System.out.println(emprestimo + " | Atraso: " + atraso + " dias");
            }
        }
    }

    private void gerarRelatorioEmprestimosAtivos() {
        System.out.println("\n=== Relatório de Empréstimos Ativos ===");

        emprestimos.stream()
            .filter(emprestimo -> emprestimo.getDataDevolucaoReal() == null)
            .sorted((e1, e2) -> e2.getDataEmprestimo().compareTo(e1.getDataEmprestimo()))
            .forEach(System.out::println);
    }

    private void realizarEmprestimo(Scanner scanner) throws OperacaoInvalidaException {
        int idLivro = obterIDValido(scanner, livros.size());
        int idUsuario = obterIDValido(scanner, usuarios.size());

        Livro livro = livros.get(idLivro);
        Usuario usuario = usuarios.get(idUsuario);

        if (livro.getNumExemplares() > 0) {
            livro.emprestarLivro();
            Date dataEmprestimo = new Date();
            Date dataDevolucaoPrevista = new Date(dataEmprestimo.getTime() + (7L * 24 * 60 * 60 * 1000));  // 7 dias depois

            Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucaoPrevista);
            emprestimos.add(emprestimo);

            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Livro sem exemplares disponíveis para empréstimo!");
        }
    }

    private void realizarDevolucao(Scanner scanner) throws OperacaoInvalidaException {
        System.out.print("Informe o ID do empréstimo para devolução: ");
        int idEmprestimo = scanner.nextInt();

        if (idEmprestimo >= 0 && idEmprestimo < emprestimos.size()) {
            Emprestimo emprestimo = emprestimos.get(idEmprestimo);

            // Atualiza data de devolução
            Date dataDevolucao = new Date();
            emprestimo.setDataDevolucaoReal(dataDevolucao);

            // Atualiza número de exemplares do livro
            Livro livro = emprestimo.getLivro();
            livro.devolverLivro();

            System.out.println("Devolução realizada com sucesso!");
        } else {
            System.out.println("Empréstimo inválido!");
        }
    }
}
