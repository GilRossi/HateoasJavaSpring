# 🛒 Product API – Spring Boot

API RESTful desenvolvida com **Spring Boot** para gerenciamento de produtos, aplicando princípios de **Clean Code**, **SOLID** e **Design Patterns**.
O projeto utiliza **Spring Data JPA**, **HATEOAS** e **Bean Validation** para fornecer uma estrutura limpa, extensível e aderente às boas práticas de desenvolvimento.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot**
* **Spring Data JPA**
* **Spring HATEOAS**
* **Bean Validation (Jakarta Validation)**
* **Maven**
* **H2 Database** (banco de dados em memória para testes)
* **RESTful API**

---

## 📂 Estrutura do Projeto

```
src/main/java/com/example/springboot
│
├── controller
│   └── ProductController.java       # Camada de entrada (Controllers REST)
│
├── dto
│   └── ProductRecordDto.java        # DTO para transporte de dados
│
├── models
│   └── ProductModel.java            # Entidade JPA representando o produto
│
├── repository
│   └── ProductRepository.java       # Interface de persistência
│
└── Application.java                 # Classe principal do Spring Boot
```

---

## 🛠 Princípios Aplicados

### **Clean Code**

* Métodos curtos e coesos
* Nomes descritivos para classes, métodos e variáveis
* Separação clara de responsabilidades (Controller, DTO, Model, Repository)
* Uso de DTOs para entrada de dados, evitando acoplamento direto com a entidade

### **SOLID**

* **S**ingle Responsibility: cada classe tem uma única responsabilidade
* **O**pen/Closed: arquitetura preparada para extensão sem modificação direta do código existente
* **L**iskov Substitution: interfaces e classes seguem contrato consistente
* **I**nterface Segregation: repositório e controller expõem apenas o necessário
* **D**ependency Inversion: uso de injeção de dependência com `@Autowired`

### **Design Patterns**

* **Repository Pattern**: abstrai o acesso a dados com `JpaRepository`
* **DTO Pattern**: desacopla entrada de dados da entidade
* **HATEOAS**: adiciona links auto-descritivos às respostas da API

---

## 📌 Endpoints da API

### Criar Produto

```http
POST /products
Content-Type: application/json

{
  "name": "Notebook Dell",
  "value": 4500.00
}
```

### Listar Produtos

```http
GET /products
```

### Buscar Produto por ID

```http
GET /products/{id}
```

### Atualizar Produto

```http
PUT /products/{id}
Content-Type: application/json

{
  "name": "Notebook Dell Atualizado",
  "value": 4800.00
}
```

### Deletar Produto

```http
DELETE /products/{id}
```

---

## 🗄 Banco de Dados

O projeto está configurado com **H2 Database** para facilitar testes locais.
Acesse o console do H2:

```
http://localhost:8080/h2-console
```

Credenciais padrão no `application.properties`.

---

## ▶ Como Executar

1. **Clonar o repositório**

```bash
git clone https://github.com/seu-usuario/product-api.git
```

2. **Entrar no diretório**

```bash
cd product-api
```

3. **Rodar o projeto**

```bash
mvn spring-boot:run
```

4. **Testar a API**
   Utilize **Postman**, **Insomnia** ou **cURL** para consumir os endpoints.

---

## 📚 Próximos Passos

* Implementar testes unitários e de integração (**JUnit 5** + **Mockito**)
* Adicionar autenticação com **Spring Security**
* Criar documentação da API com **Swagger/OpenAPI**
* Adicionar cache com **Spring Cache**

---

## 👨‍💻 Autor

**Gil Rossi Aguiar**
📧 [gilrossi.aguiar@live.com](mailto:gilrossi.aguiar@live.com)
💼 [LinkedIn](https://www.linkedin.com/in/gil-rossi-5814659b/)
🐙 [GitHub]([https://github.com/seu-usuario](https://github.com/GilRossi))
