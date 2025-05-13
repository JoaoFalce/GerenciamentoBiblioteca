package view;

import model.Livro;
import model.Usuario;
import model.Emprestimo;

import java.util.List;

public class VisaoBiblioteca {

    private final List<Livro> livros;
    private final List<Usuario> usuarios;
    private final List<Emprestimo> emprestimos;

    public VisaoBiblioteca(List<Livro> livros, List<Usuario> usuarios, List<Emprestimo> emprestimos) {
        this.livros = livros;
        this.usuarios = usuarios;
        this.emprestimos = emprestimos;
    }

    public void exibirMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Biblioteca ===");
        System.out.println("1. Listar Livros");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Listar Empréstimos");
        System.out.println("4. Gerar Relatório de Atrasos");
        System.out.println("5. Gerar Relatório de Empréstimos Ativos");
        System.out.println("6. Realizar Empréstimo");
        System.out.println("7. Realizar Devolução");
        System.out.println("8. Sair");
    }

    public void listarLivros() {
        System.out.println("\n=== Lista de Livros ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public void listarUsuarios() {
        System.out.println("\n=== Lista de Usuários ===");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public void listarEmprestimos() {
        System.out.println("\n=== Lista de Empréstimos ===");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }
}
