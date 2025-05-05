package view;

import model.Livro;
import model.Usuario;
import model.Emprestimo;
import java.util.ArrayList;
import java.util.List;

public class PreCarregadorDados {
    
    private static List<Livro> livros = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();

    public static void carregarDadosIniciais() {
        // Exemplo de livros e usuários com dados iniciais
        livros.add(new Livro("Livro A", "Autor A", 2021, 10, "Ficção"));
        livros.add(new Livro("Livro B", "Autor B", 2019, 5, "História"));

        usuarios.add(new Usuario("Usuario A", "123456789", "Rua A, 123", "usera@gmail.com"));
        usuarios.add(new Usuario("Usuario B", "987654321", "Rua B, 456", "userb@gmail.com"));

        // Adicione mais dados conforme necessário
    }

    public static List<Livro> getLivros() {
        return livros;
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
