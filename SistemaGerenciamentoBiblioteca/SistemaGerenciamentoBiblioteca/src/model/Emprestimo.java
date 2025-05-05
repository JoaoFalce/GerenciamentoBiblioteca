package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;

    public Emprestimo(Livro livro, Usuario usuario, Date dataEmprestimo, Date dataDevolucaoPrevista) throws OperacaoInvalidaException {
        if (livro == null || usuario == null) {
            throw new OperacaoInvalidaException("Livro e usuário são obrigatórios");
        }
        if (dataEmprestimo == null || dataDevolucaoPrevista == null) {
            throw new OperacaoInvalidaException("As datas de empréstimo e devolução são obrigatórias");
        }

        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    // Sobrescrever o toString()
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String emprestimo = sdf.format(dataEmprestimo);
        String devolucaoPrevista = sdf.format(dataDevolucaoPrevista);
        String devolucaoReal = (dataDevolucaoReal != null) ? sdf.format(dataDevolucaoReal) : "Não devolvido";

        return "Livro:\n" + livro + 
               "\nUsuário:\n" + usuario + 
               "\nData de Empréstimo: " + emprestimo +
               "\nData de Devolução Prevista: " + devolucaoPrevista +
               "\nData de Devolução Real: " + devolucaoReal + "\n";
    }

    // Getters e setters
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Date getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(Date dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    // Método para calcular atraso
    public int calcularAtraso() {
        if (dataDevolucaoReal != null) {
            long diferenca = dataDevolucaoReal.getTime() - dataDevolucaoPrevista.getTime();
            return (int) (diferenca / (1000 * 60 * 60 * 24)); // Convertendo de milissegundos para dias
        }
        return 0;
    }
}
