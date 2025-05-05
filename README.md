# GerenciamentoBiblioteca
Este projeto consiste em um sistema de gerenciamento de biblioteca desenvolvido em Java, que permite realizar operações como empréstimo, devolução, e consulta de livros, usuários e empréstimos. A aplicação segue o padrão MVC (Model-View-Controller), com a separação clara entre a lógica de negócios, a interface de usuário e o controle de operações.

Funcionalidades
Empréstimo de livros: Realize empréstimos de livros disponíveis na biblioteca.

Devolução de livros: Registre a devolução dos livros e atualize o estoque.

Relatórios:

Relatório de livros com atraso na devolução.

Relatório de empréstimos ativos.

Gestão de usuários e livros: Liste os livros e usuários cadastrados no sistema.



Estrutura do Projeto:

O projeto segue a arquitetura MVC, separando as responsabilidades entre o Modelo, Visão e Controle:

Modelo (Model): Contém as classes que representam os dados e regras de negócios, como Livro, Usuario e Emprestimo.

Visão (View): Responsável pela interface com o usuário, com a classe VisaoBiblioteca exibindo as informações e menus.

Controle (Controller): Contém a lógica de controle da aplicação, onde o fluxo de dados é gerenciado. A classe ControladorBiblioteca orquestra as operações entre as diferentes camadas.



Requisitos:
Java 8 ou superior.



Clone o repositório para sua máquina local.



Melhorias Futuras:

Implementar persistência de dados utilizando um banco de dados.

Adicionar autenticação de usuários para operações de empréstimo.

Melhorar a interface com a implementação de uma GUI (Interface Gráfica).

