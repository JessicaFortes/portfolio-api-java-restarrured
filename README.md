# Portfólio Testes de API - Automação de Testes

Projeto de testes automatizados para a API de usuários (CRUD), utilizando Java, JUnit 5 e RestAssured, com base na API pública do ServeRest.

## Escopo dos Testes

**Endpoints cobertos:**

- `GET /usuarios`
- `POST /usuarios`
- `GET /usuarios/{id}`
- `PUT /usuarios/{id}`
- `DELETE /usuarios/{id}`

**API alvo:**

- `https://serverest.dev`

## Stack

- Java 21
- Maven
- JUnit 5
- RestAssured
- Hamcrest
- Datafaker

## Pré-requisitos

- Java 21
- Maven 3.9+
- Acesso à internet para a API `serverest.dev`

## Como executar

### Rodar todos os testes

```bash
mvn clean test
```

### Rodar com relatório HTML

```bash
mvn clean verify
```

**Relatório HTML:**

- `target/site/surefire-report.html`

## Estrutura de dados e massa

As massas de dados estão localizadas em `data/users.json`.

O arquivo possui 3 massas principais:

- `consulta`
- `alteracao`
- `remover`

Cada massa possui os campos:

- `nome`
- `email`
- `password`
- `administrador`
- `_id` (atualizado automaticamente)

## Seed automático antes dos testes

Antes de executar os testes, o projeto realiza um seed automático que:

1. Lê `data/users.json`
2. Cria usuários via `POST /usuarios`
3. Atualiza os `_id` retornados no próprio arquivo

Isso garante que os testes utilizem usuários existentes e válidos.

## Casos cobertos

### GET /usuarios

- Listar todos os usuários
- Filtrar por:
  - `_id`
  - `nome`
  - `email`
  - `password`
  - `administrador`
- Validações de schema e quantidade

### GET /usuarios/{id}

- Buscar usuário existente
- Usuário inexistente
- ID com tamanho inválido

### POST /usuarios

- Cadastro de usuário administrador
- Cadastro de usuário não administrador
- Validações de obrigatoriedade:
  - `nome`
  - `email`
  - `password`
  - `administrador`
- E-mail já existente
- Body vazio

### PUT /usuarios/{id}

- Alterar apenas e-mail
- Alterar apenas senha
- Alterar apenas nome
- Alterar apenas administrador
- Alterar todos os campos
- ID inexistente
- Body vazio

### DELETE /usuarios/{id}

- Remover usuário existente
- Remover usuário inexistente
- Tentativa de remover usuário com carrinho

## Relatórios na pipeline

O GitHub Actions gera e publica:

- Logs do Surefire em `target/surefire-reports`
- Relatório HTML em `target/site/surefire-report.html`

## Observações

- A API possui rate limit (100 req/min). Evite alto paralelismo.
- O projeto utiliza JWT via `/login` e `Authorization: Bearer <token>`.

## Execução via GitHub Actions

O pipeline executa:

```bash
mvn clean verify
```

**Artefatos:**

- `target/surefire-reports`
- `target/site`