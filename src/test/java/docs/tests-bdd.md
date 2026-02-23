# Descrição dos Testes (BDD)

Este documento descreve os cenários dos testes automatizados em formato BDD (Given/When/Then).

---

## BaseApiTest (Infra)

**Objetivo:** garantir o preparo da massa de dados e a autenticação antes da execução dos testes.

**Given** um token válido e o seed configurado  
  **When** a suíte de testes é iniciada  
  **Then** os usuários de `data/users.json` são criados/atualizados e os `_id` são persistidos.

---

## GetUsersTest

**Objetivo:** validar a listagem e os filtros de usuários.

**Given** que a API de usuários está disponível  
  **When** consultar `GET /usuarios`  
  **Then** retorna uma lista de usuários com schema válido e quantidade maior que zero.

**Given** um usuário existente (massa `consulta`)  
  **When** consultar `GET /usuarios` com filtro `_id`  
  **Then** retorna apenas 1 registro com o `_id` informado.

**Given** um usuário existente (massa `consulta`)  
  **When** consultar `GET /usuarios` com filtro `nome`  
  **Then** todos os usuários retornados contêm o nome informado.

**Given** um usuário existente (massa `consulta`)  
  **When** consultar `GET /usuarios` com filtro `password`  
  **Then** todos os usuários retornados contêm a senha informada.

**Given** usuários administradores  
  **When** consultar `GET /usuarios` com `administrador=true`  
  **Then** todos os usuários retornados são administradores.

**Given** usuários não administradores  
  **When** consultar `GET /usuarios` com `administrador=false`  
  **Then** todos os usuários retornados não são administradores.

**Given** um usuário existente (massa `consulta`)  
  **When** consultar `GET /usuarios` com filtro `email`  
  **Then** todos os usuários retornados possuem o e-mail informado.

**Given** um `_id` inexistente  
  **When** consultar `GET /usuarios` com `_id` inválido  
  **Then** retorna lista vazia.

---

## GetUsersByIdTest

**Objetivo:** validar a busca direta por ID.

**Given** um usuário existente (massa `consulta`)  
  **When** consultar `GET /usuarios/{id}`  
  **Then** retorna o usuário e o `_id` confere com o solicitado.

**Given** um `_id` inexistente  
  **When** consultar `GET /usuarios/{id}`  
  **Then** retorna mensagem de usuário não encontrado.

**Given** um `_id` com tamanho maior que o permitido  
  **When** consultar `GET /usuarios/{id}`  
  **Then** retorna erro de validação de tamanho.

**Given** um `_id` com tamanho menor que o permitido  
  **When** consultar `GET /usuarios/{id}`  
  **Then** retorna erro de validação de tamanho.

---

## PostUsersTest

**Objetivo:** validar o cadastro de usuários.

**Given** dados válidos de usuário administrador  
  **When** enviar `POST /usuarios`  
  **Then** o usuário é criado com sucesso.

**Given** dados válidos de usuário não administrador  
  **When** enviar `POST /usuarios`  
  **Then** o usuário é criado com sucesso.

**Given** ausência do campo `administrador`  
  **When** enviar `POST /usuarios`  
  **Then** retorna erro de campo obrigatório.

**Given** ausência do campo `email`  
  **When** enviar `POST /usuarios`  
  **Then** retorna erro de campo obrigatório.

**Given** ausência do campo `nome`  
  **When** enviar `POST /usuarios`  
  **Then** retorna erro de campo obrigatório.

**Given** ausência do campo `password`  
  **When** enviar `POST /usuarios`  
  **Then** retorna erro de campo obrigatório.

**Given** um e-mail já cadastrado  
  **When** enviar `POST /usuarios`  
  **Then** retorna erro de e-mail duplicado.

**Given** body vazio  
  **When** enviar `POST /usuarios`  
  **Then** retorna erros de campos obrigatórios.

---

## PutUsersByIdTest

**Objetivo:** validar a atualização de usuários.

**Given** um usuário existente (massa `alteracao`)  
  **When** enviar `PUT /usuarios/{id}` alterando apenas o e-mail  
  **Then** retorna sucesso de alteração.

**Given** um usuário existente (massa `alteracao`)  
  **When** enviar `PUT /usuarios/{id}` alterando apenas a senha  
  **Then** retorna sucesso de alteração.

**Given** um usuário existente (massa `alteracao`)  
  **When** enviar `PUT /usuarios/{id}` alterando apenas o nome  
  **Then** retorna sucesso de alteração.

**Given** um usuário existente (massa `alteracao`)  
  **When** enviar `PUT /usuarios/{id}` alterando apenas o campo administrador  
  **Then** retorna sucesso de alteração.

**Given** um usuário existente (massa `alteracao`)  
  **When** enviar `PUT /usuarios/{id}` alterando todos os campos  
  **Then** retorna sucesso de alteração.

**Given** um `_id` inexistente  
  **When** enviar `PUT /usuarios/{id}`  
  **Then** a API cria um novo usuário (comportamento específico da API).

**Given** body vazio  
  **When** enviar `PUT /usuarios/{id}`  
  **Then** retorna erros de campos obrigatórios.

---

## DeleteUsersByIdTest

**Objetivo:** validar a remoção de usuários.

**Given** um usuário existente (massa `remover`)  
  **When** enviar `DELETE /usuarios/{id}`  
  **Then** o usuário é removido com sucesso.

**Given** um `_id` inexistente  
  **When** enviar `DELETE /usuarios/{id}`  
  **Then** retorna mensagem de nenhum registro excluído.

**Given** um usuário com carrinho  
  **When** enviar `DELETE /usuarios/{id}`  
  **Then** retorna erro informando que não é permitido excluir usuário com carrinho.



Observação: Este texto foi criado, revisado e ajustado com o auxílio de Inteligência Artificial.