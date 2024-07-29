# Project Guide

## 1 - Criar um novo projeto Spring Boot 

Para um sistema de vendas online para uma livraria usando Spring Boot, você precisará de várias dependências para lidar com diferentes aspectos do sistema, como a camada web, acesso a dados, segurança, entre outros. Aqui estão algumas dependências recomendadas:

1. **Spring Web**: Para criar endpoints REST e lidar com requisições HTTP.
2. **Spring Data JPA**: Para interagir com o banco de dados usando JPA (Java Persistence API).
3. **Spring Security**: Para adicionar segurança à sua aplicação, como autenticação e autorização.
4. **H2 Database** (ou outro banco de dados, como MySQL ou PostgreSQL): Para desenvolvimento e testes, você pode usar o H2 Database. Em produção, você pode optar por MySQL, PostgreSQL ou outro banco de dados relacional.
5. **Thymeleaf** (opcional): Se você pretende ter páginas HTML renderizadas no servidor.
6. **Spring Boot DevTools**: Para facilitar o desenvolvimento, com recarregamento automático de código.
7. **Spring Boot Actuator**: Para monitorar e gerenciar sua aplicação.

Vamos detalhar cada uma delas no Spring Initializr:

- **Group**: `com.example`
- **Artifact**: `bookstore`
- **Name**: `Bookstore`
- **Description**: `Online bookstore sales system`
- **Package name**: `com.example.bookstore`
- **Packaging**: `Jar`
- **Java Version**: `11` ou `17` (dependendo da versão que você está usando)

### Dependências:
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (ou outra de sua escolha como MySQL, PostgreSQL)
- **Thymeleaf** (opcional)
- **Spring Boot DevTools**
- **Spring Boot Actuator**

### Passo a Passo:
1. **Acesse o [Spring Initializr](https://start.spring.io/)**.
2. **Preencha os campos do projeto** com as informações mencionadas acima.
3. **Adicione as dependências**:
    - No campo de dependências, adicione:
        - `Spring Web`
        - `Spring Data JPA`
        - `Spring Security`
        - `H2 Database` (ou outro de sua escolha)
        - `Thymeleaf` (se necessário)
        - `Spring Boot DevTools`
        - `Spring Boot Actuator`
4. **Clique em "Generate"** para baixar o projeto configurado.

### No IntelliJ:
5. **Extraia o arquivo zip** baixado.
6. **Abra o IntelliJ IDEA** e selecione **"Open"**.
7. **Navegue até a pasta do projeto extraído** e selecione-a para abrir no IntelliJ.
8. **Aguarde o IntelliJ importar o projeto e baixar as dependências** do Maven.

Pronto! Agora você está com o projeto Spring Boot inicializado com as dependências básicas para começar a desenvolver seu sistema de vendas online para uma livraria.

Listagem de caminhos de pasta


``` shell
bookstore:.
|   .gitignore
|   HELP.md
|   mvnw
|   mvnw.cmd
|   pom.xml
|   ProjectGuide.md
|
+---.idea
|       .gitignore
|       compiler.xml
|       encodings.xml
|       jarRepositories.xml
|       jpa-buddy.xml
|       misc.xml
|       workspace.xml
|
+---.mvn
|   \---wrapper
|           maven-wrapper.properties
|
\---src
+---main
|   +---java
|   |   \---one
|   |       \---digitalinnovation
|   |           \---bookstore
|   |                   BookstoreApplication.java
|   |
|   \---resources
|       |   application.properties
|       |
|       +---static
|       \---templates
\---test
\---java
\---one
\---digitalinnovation
\---bookstore
BookstoreApplicationTests.java
```

## 2 - Configurar o banco de dados H2 no arquivo application.properties.

Para configurar o banco de dados H2 no arquivo `application.properties`, você precisa adicionar as configurações necessárias para conectar ao banco de dados. Aqui está um exemplo básico de configuração:

```properties
# Nome da aplicação
spring.application.name=Bookstore

# Configurações do H2 Database
spring.datasource.url=jdbc:h2:mem:bookstoredb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

# Configurações JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

### Explicação das Configurações:
- `spring.datasource.url` define a URL de conexão do banco de dados H2. `jdbc:h2:mem:bookstoredb` configura um banco de dados em memória chamado `bookstoredb`.
- `spring.datasource.driverClassName` define a classe do driver JDBC para o H2.
- `spring.datasource.username` e `spring.datasource.password` configuram as credenciais de acesso ao banco de dados. O H2 usa `sa` como nome de usuário padrão e uma senha vazia.
- `spring.h2.console.enabled=true` habilita o console web do H2, acessível em `http://localhost:8080/h2-console`.
- `spring.jpa.database-platform` define o dialeto do Hibernate para o H2.
- `spring.jpa.hibernate.ddl-auto=update` configura o Hibernate para atualizar automaticamente o esquema do banco de dados com base nas entidades JPA definidas na aplicação.

Salve as alterações no `application.properties` após adicionar essas configurações.

## 3 - Criar as entidades JPA que representarão as tabelas do banco de dados.

O próximo passo é criar as entidades JPA que representarão as tabelas do banco de dados. Vamos começar criando a entidade `Book`.

Crie um arquivo Java chamado `Book.java` no diretório `src/main/java/one/digitalinnovation/bookstore` com o seguinte conteúdo:

```java
package one.digitalinnovation.bookstore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private double price;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
```

Isso cria a entidade `Book` com os campos `id`, `title`, `author`, `isbn` e `price`.

## 4 - Criar os repositórios JPA para as entidades 

O próximo passo é criar os repositórios JPA para as entidades, permitindo que você realize operações de CRUD no banco de dados.

Comece criando um repositório para a entidade `Book`.

Crie um arquivo Java chamado `BookRepository.java` no diretório `src/main/java/one/digitalinnovation/bookstore` com o seguinte conteúdo:

```java
package one.digitalinnovation.bookstore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
```

Isso cria um repositório JPA para a entidade `Book`, permitindo que você execute operações de CRUD usando a interface `JpaRepository`.

## 5 - Criar serviços para a entidade Book

O próximo passo é criar serviços que encapsulam a lógica de negócios relacionada à entidade `Book`. Os serviços serão responsáveis por interagir com os repositórios e realizar operações como criação, leitura, atualização e exclusão de livros.

### Criação do BookService

Crie um arquivo Java chamado `BookService.java` no diretório `src/main/java/one/digitalinnovation/bookstore` com o seguinte conteúdo:

```java
package one.digitalinnovation.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book update(Book book, Long id) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setPrice(book.getPrice());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }
}
```

### Explicação dos métodos do BookService

1. **findAll**: Retorna uma lista com todos os livros.
2. **findById**: Retorna um livro específico com base no ID fornecido.
3. **save**: Salva um novo livro no banco de dados.
4. **deleteById**: Exclui um livro com base no ID fornecido.
5. **update**: Atualiza um livro existente com base no ID fornecido.

### Dependências

Certifique-se de que o `BookService` tenha as seguintes anotações e imports:

- `@Service`: Define a classe como um serviço Spring, que é detectado automaticamente durante a varredura de componentes.
- `@Autowired`: Injeta automaticamente a dependência do `BookRepository` no `BookService`.

Com o `BookService` criado, agora você pode continuar para o próximo passo, que é criar os controladores para expor os endpoints REST para a entidade `Book`.

## 6 - Criar os controladores REST

O próximo passo é criar os controladores REST (REST controllers) para expor endpoints que permitirão interagir com as entidades através de requisições HTTP. Vamos começar criando um controlador para a entidade `Book`.

### 1. Criar o controlador REST para `Book`

Crie um arquivo Java chamado `BookController.java` no diretório `src/main/java/one/digitalinnovation/bookstore` com o seguinte conteúdo:

```java
package one.digitalinnovation.bookstore;

import one.digitalinnovation.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id) {
        return bookService.update(book, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

### Explicação dos métodos do BookController

1. **getAllBooks**: Retorna uma lista com todos os livros.
2. **getBookById**: Retorna um livro específico com base no ID fornecido.
3. **createBook**: Cria um novo livro.
4. **updateBook**: Atualiza um livro existente com base no ID fornecido.
5. **deleteBook**: Exclui um livro com base no ID fornecido.

### Dependências

Certifique-se de que o `BookController` tenha as seguintes anotações e imports:

- `@RestController`: Define a classe como um controlador REST.
- `@RequestMapping`: Define o caminho base para todos os endpoints do controlador.
- `@Autowired`: Injeta automaticamente a dependência do `BookService` no `BookController`.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Anotações que mapeiam os métodos HTTP para os métodos do controlador.

Com o controlador REST para `Book` criado, você agora tem endpoints para gerenciar a entidade `Book`. Repita esse processo para as outras entidades (`Author`, `Category`, `Customer`, `Order`, `OrderItem`, `Publisher`) conforme necessário.

## 7 - Testar os endpoints da API

O próximo passo é **testar os endpoints da API** para garantir que todas as operações CRUD estão funcionando corretamente.

Você pode fazer isso usando ferramentas como Postman, cURL, ou escrevendo testes de integração com Spring Boot Test.



