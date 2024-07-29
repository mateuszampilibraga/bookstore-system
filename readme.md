# Bookstore API

## Descrição

Este é um projeto de API para gerenciar uma livraria. A API permite o gerenciamento de livros, autores, categorias, clientes, pedidos e editores. É construído com Spring Boot e JPA, utilizando práticas modernas de desenvolvimento de software.

## Funcionalidades

- **Gerenciamento de Livros:** Adicionar, atualizar, remover e listar livros.
- **Gerenciamento de Autores:** Adicionar, atualizar, remover e listar autores.
- **Gerenciamento de Categorias:** Adicionar, atualizar, remover e listar categorias.
- **Gerenciamento de Clientes:** Adicionar, atualizar, remover e listar clientes.
- **Gerenciamento de Pedidos:** Criar, atualizar, remover e listar pedidos.
- **Gerenciamento de Editores:** Adicionar, atualizar, remover e listar editores.

## Tecnologias Utilizadas

- **Spring Boot** - Framework para construção da aplicação.
- **JPA (Java Persistence API)** - Para gerenciamento de persistência de dados.
- **H2 Database** - Banco de dados em memória para desenvolvimento e testes.

## Requisitos

- Java 11 ou superior
- Maven

## Instalação

1. Clone o repositório:

    ```bash
    git clone https://github.com/mateuszampilibraga/bookstore-system.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd bookstore-system
    ```

3. Compile e execute a aplicação:

    ```bash
    mvn spring-boot:run
    ```

## Uso

A API está disponível na URL padrão do Spring Boot: `http://localhost:8080`.

### Exemplos de Requisições

- **Obter todos os livros:**

    ```bash
    curl -X GET http://localhost:8080/api/books
    ```

- **Adicionar um novo livro:**

    ```bash
    curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d '{"title": "New Book", "author": "Author Name", "isbn": "123456789", "price": 19.99}'
    ```

## Testes

Para executar os testes, utilize o comando:

```bash
mvn test
```

## Contribuição

Sinta-se à vontade para contribuir com melhorias e correções. Faça um fork do repositório, crie uma branch para suas alterações, e envie um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](https://github.com/mateuszampilibraga/bookstore-system/blob/master/LICENSE).

## Contato

Se tiver dúvidas ou precisar de ajuda, entre em contato:

- [Linkedin](https://www.linkedin.com/in/mateuszampilibraga/)

